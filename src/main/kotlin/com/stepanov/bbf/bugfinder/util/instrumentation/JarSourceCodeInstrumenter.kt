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
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import kotlin.system.exitProcess

class JarSourceCodeInstrumenter(
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

    fun instrument(targetCoverage: List<CoverageEntry>) {
        if (File(newJarPath).exists()) File(newJarPath).delete()
        val outputStream = JarOutputStream(FileOutputStream(File(newJarPath)))
        val jarFile = JarFile(jarPath)
        writeCoverageClassesToJar(outputStream)
        val jarEntries = jarFile.entries().toList()
        val entriesToInstrument =
            if (targetCoverage.isNotEmpty()) {
                val classNames = targetCoverage.map { it.pathToFun.substringAfterLast('.') }.toSet()
                classNames.flatMap { className -> jarEntries.filter { it.name.contains(className, true) } }.toSet()
            } else {
                jarEntries
            }.toHashSet()
        var psiSourceCode: PsiFile? = null
        var prevFullClassName = ""
        var namedFunctionsToLineNumber: List<Pair<IntRange, CoverageEntry>> = listOf()
        //Instrumentation
        val entriesSize = jarEntries.size
        for ((i, entry) in jarEntries.withIndex()) {
            //if (i < 17589) continue
            val className = entry.realName.substringBeforeLast(".class").replace('/', '.')
            println("$i from $entriesSize $className")
            if (!entry.name.endsWith(".class")
                || entry.name.startsWith("META-INF")
                || entry.name.startsWith("coverage")
                || entry.name.contains("protobuf", true)
                || entry !in entriesToInstrument
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
            //if (!fullClassName.contains("JvmLower")) continue
//            val sourceFileInputStream = JarFile(sourceCodeJarPath).getInputStream(sourceFileEntry)
//            val sourceCode = sourceFileInputStream.bufferedReader().readText()
            val sourceCode = readTextFromEntry(sourceFileEntry)
            if (sourceCode == null) {
                addEntry(entry, jarFile, outputStream)
                continue
            }
            psiSourceCode =
                when {
                    fullClassName == prevFullClassName -> psiSourceCode
                    classNode.sourceFile.endsWith("kt") -> PSICreator.getPSIForText(sourceCode, false)
                    else -> PSICreator.getPsiForJava(sourceCode)
                }
            // println("PSI BUILT")
            namedFunctionsToLineNumber =
                when {
                    fullClassName == prevFullClassName -> namedFunctionsToLineNumber
                    psiSourceCode is KtFile -> {
                        val funcs =
                            psiSourceCode
                                .getAllPSIChildrenOfType<KtNamedFunction>()
                                .filter { it.nameIdentifier != null }
                        funcs
                            .zip(KotlinSignatureCollector().collect(funcs))
                            .map { it.first.getSourceCodeLinesRange() to it.second }
                    }
                    else -> {
                        val funcs =
                            psiSourceCode!!
                                .getAllPSIChildrenOfType<PsiMethod>()
                                .filter { it.nameIdentifier != null }
                        funcs
                            .zip(JavaSignatureCollector().collect(funcs))
                            .map { it.first.getSourceCodeLinesRange() to it.second }
                    }
                }
            prevFullClassName = fullClassName
            //  println("FUN TO LINE NUMBER")
            val methods = classNode.methods
            for ((j, m) in methods.withIndex()) {
                // println("Method $j from ${methods.size}")
                val accessAsBinary = m.access.toString(2).reversed()
                //We cannot insert code in abstract methods isAbstract
                if (accessAsBinary.length >= abstractOpcodeLength && accessAsBinary[abstractOpcodeLength - 1] == '1') continue
                val instructions = m.instructions
                val methodName = m.name.substringAfterLast('$')
                val lineNumbers = m.instructions.filterIsInstance<LineNumberNode>().map { it.line }
                if (lineNumbers.isEmpty()) continue
                //Avoid mangling by methodName.substringBefore('-')
                val coverageEntryInPsi =
                    namedFunctionsToLineNumber
                        .filter { it.second.methodName == methodName.substringBefore('-') }
                        .minByOrNull { (range, _) ->
                            lineNumbers.sumBy {
                                when {
                                    it in range -> 0
                                    it < range.first -> range.first - it
                                    else -> range.last - it
                                }
                            }
                        }?.second ?: continue
                //println("${methodInPsi != null} ${lineNumbers.minOrNull()} NAME = ${m.name} NAME IN PSI = ${methodInPsi?.methodName}")
                val newInstructionList = listOf(
                    TypeInsnNode(Opcodes.NEW, "coverage/CoverageEntry"),
                    InsnNode(Opcodes.DUP),
                    LdcInsnNode(coverageEntryInPsi.pathToFun),
                    LdcInsnNode(coverageEntryInPsi.methodName),
                    LdcInsnNode(coverageEntryInPsi.parameters.joinToString(";")),
                    LdcInsnNode(coverageEntryInPsi.returnType),
                    MethodInsnNode(
                        Opcodes.INVOKESPECIAL,
                        "coverage/CoverageEntry",
                        "<init>",
                        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V"
                    ),
                    MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "coverage/MyMethodBasedCoverage",
                        "putEntry",
                        "(Lcoverage/CoverageEntry;)V"
                    )
                ).reversed()
                newInstructionList.forEach { instructions.insert(it) }
                m.maxStack += 6
            }
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