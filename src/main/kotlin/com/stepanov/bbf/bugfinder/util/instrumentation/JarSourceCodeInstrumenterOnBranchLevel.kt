package com.stepanov.bbf.bugfinder.util.instrumentation

import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethod
import com.stepanov.bbf.bugfinder.gitinfocollector.JavaSignatureCollector
import com.stepanov.bbf.bugfinder.gitinfocollector.KotlinSignatureCollector
import com.stepanov.bbf.bugfinder.util.getAllPSIChildrenOfType
import com.stepanov.bbf.bugfinder.util.getAllWithoutLast
import com.stepanov.bbf.bugfinder.util.getSourceCodeLinesRange
import com.stepanov.bbf.reduktor.parser.PSICreator
import coverage.CoverageEntry
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.*
import org.objectweb.asm.util.CheckClassAdapter
import org.objectweb.asm.util.Textifier
import org.objectweb.asm.util.TraceMethodVisitor
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import kotlin.system.exitProcess


val printer = Textifier()
val mp = TraceMethodVisitor(printer)

fun ClassNode.print() = buildString {
    appendLine("Class $name")
    for (mn in methods) {
        appendLine(mn.print())
    }
}

fun MethodNode.print() = buildString {
    appendLine(name)
    for (insn in instructions) {
        append(insn.print())
    }
    for (insn in tryCatchBlocks) {
        append(insn.print())
    }
}

fun AbstractInsnNode.print(): String {
    this.accept(mp)
    val sw = StringWriter()
    printer.print(PrintWriter(sw))
    printer.getText().clear()
    return sw.toString()
}

fun TryCatchBlockNode.print() = buildString {
    append("${start.print().dropLast(1)} ")
    append("${end.print().dropLast(1)} ")
    append("${handler.print().dropLast(1)} ")
    appendLine(type ?: "java/lang/Throwable")
}


class JarSourceCodeInstrumenterOnBranchLevel(
    private val jarPath: String,
    private val sourceCodeJarPaths: List<String>,
    private val newJarPath: String
) : JarInstrumenter() {

    private val sourceJarFileEntries =
        sourceCodeJarPaths.flatMap {
            JarFile(it).entries().toList()
        }

    fun readTextFromEntry(jarEntry: JarEntry): String? {
        for (jarPath in sourceCodeJarPaths) {
            val jarFile = JarFile(jarPath)
            val neededEntry = jarFile.entries().toList().find { "$it" == "$jarEntry" }
            if (neededEntry != null) {
                return jarFile.getInputStream(neededEntry).bufferedReader().readText()
            }
        }
        return null
    }

    fun searchForEntryInSourceFile(entryPath: String): JarEntry? =
        sourceJarFileEntries.find { it.name == entryPath }

    fun instrument(targetCoverage: List<Pair<String, List<Int>>>) {
        if (File(newJarPath).exists()) File(newJarPath).delete()
        val outputStream = JarOutputStream(FileOutputStream(File(newJarPath)))
        val jarFile = JarFile(jarPath)
        writeBranchCoverageClassesToJar(outputStream)
        val jarEntries = jarFile.entries().toList()
        //Instrumentation
        val entriesSize = jarEntries.size
        var branchUID = 0
        for ((i, entry) in jarEntries.withIndex()) {
            val className = entry.realName.substringBeforeLast(".class").replace('/', '.')
            println("$i from $entriesSize $className")
            if (!entry.name.endsWith(".class")
                || entry.name.startsWith("META-INF")
                || entry.name.startsWith("coverage")
                || entry.name.contains("protobuf", true)
            ) {
                addEntry(entry, jarFile, outputStream)
                continue
            }
            val reader = ClassReader(jarFile.getInputStream(entry))
            val classNode = ClassNode()
            reader.accept(classNode, 0)
            val path = classNode.name.split('/').getAllWithoutLast().joinToString("/")
            if (!path.contains("kotlin")) {
                addEntry(entry, jarFile, outputStream)
                continue
            }
            println("Class name source = $path/${classNode.sourceFile}")
            val fullClassName = "$path/${classNode.sourceFile}"
            val sourceFileEntry = sourceJarFileEntries.find { it.realName == fullClassName }
            if (sourceFileEntry == null) {
                println("Cant find file source for $fullClassName")
                addEntry(entry, jarFile, outputStream)
                continue
                //exitProcess(0)
            }
            val targetCoverageEntry = targetCoverage.find { it.first.endsWith(sourceFileEntry.realName) }
            if (targetCoverageEntry == null) {
                println("${sourceFileEntry.realName} not in targetCoverage")
                addEntry(entry, jarFile, outputStream)
                continue
            }
            val sourceCode = readTextFromEntry(sourceFileEntry)
            if (sourceCode == null) {
                addEntry(entry, jarFile, outputStream)
                continue
            }
            val methods = classNode.methods
            val branchInstructionsOpcodes = (153..166).toList() + listOf(170, 171, 198, 199)
            val handledLabels = mutableListOf<AbstractInsnNode>()
            for ((j, m) in methods.withIndex()) {
                var curLineNumber: LineNumberNode? = null
                val resList = mutableListOf<Pair<AbstractInsnNode, LineNumberNode?>>()
                m.instructions.map {
                    if (it is LineNumberNode) curLineNumber = it
                    if (it.opcode in branchInstructionsOpcodes) {
                        if (curLineNumber?.line in targetCoverageEntry.second) {
                            resList.add(it to curLineNumber)
                        }
                    }
                }
                for ((jumpInst, lineNumber) in resList) {
                    lineNumber?.line ?: continue
                    println("LOLOLOLOL")
                    val labels =
                        when (jumpInst) {
                            is JumpInsnNode -> listOf(jumpInst, jumpInst.label)
                            is TableSwitchInsnNode -> jumpInst.labels + listOf(jumpInst.dflt)
                            is LookupSwitchInsnNode -> jumpInst.labels + listOf(jumpInst.dflt)
                            else -> listOf()
                        }
                    for (label in labels) {
                        if (handledLabels.contains(label)) continue
                        handledLabels.add(label)
                        val newInstructionList2 = listOf(
                            TypeInsnNode(Opcodes.NEW, "coverage/BranchCoverageEntry"),
                            InsnNode(Opcodes.DUP),
                            LdcInsnNode(branchUID++),
                            LdcInsnNode(classNode.name),
                            LdcInsnNode(lineNumber.line),
                            InsnNode(Opcodes.ICONST_0),
                            MethodInsnNode(
                                Opcodes.INVOKESPECIAL,
                                "coverage/BranchCoverageEntry",
                                "<init>",
                                "(ILjava/lang/String;II)V"
                            ),
                            MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "coverage/MyBranchBasedCoverage",
                                "putEntry",
                                "(Lcoverage/BranchCoverageEntry;)V"
                            )
                        ).reversed()
                        newInstructionList2.forEach { m.instructions.insert(label, it) }
                    }
                }
                m.maxStack += 10
            }
            //continue
            val outData = ClassWriter(ClassWriter.COMPUTE_FRAMES)
            try {
                println("WRITING TO $fullClassName")
                val cca = CheckClassAdapter(outData)
                classNode.accept(cca)
            } catch (e: Exception) {
                println("OOPS!!")
            }
            val out = FileOutputStream("tmp/tmp.class")
            out.write(outData.toByteArray())
            out.close()
            addFileToJar(outputStream, File("tmp/tmp.class"), entry.name)
        }
        outputStream.close()
//        exitProcess(0)
    }
}
