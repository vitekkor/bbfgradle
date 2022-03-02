package com.stepanov.bbf.bugfinder.util.instrumentation

import com.stepanov.bbf.bugfinder.util.getAllWithoutLast
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

class JarSourceCodeInstrumenterOnLineLevel(
    private val jarPath: String,
    private val sourceCodeJarPaths: List<String>,
    private val newJarPath: String
) : JarInstrumenter() {

    private val sourceJarFileEntries =
        sourceCodeJarPaths.flatMap {
            JarFile(it).entries().toList()
        }

    private fun readTextFromEntry(jarEntry: JarEntry): String? {
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

    fun instrument(
        targetCoverage: List<Pair<String, List<Int>>>,
        withCoverageClasses: Boolean
    ): List<Pair<Pair<String, Int>, Int>> {
        val instrumentedCode = mutableListOf<Pair<Pair<String, Int>, Int>>()
        if (File(newJarPath).exists()) File(newJarPath).delete()
        val outputStream = JarOutputStream(FileOutputStream(File(newJarPath)))
        val jarFile = JarFile(jarPath)
        if (withCoverageClasses) {
            writeLineCoverageClassesToJar(outputStream)
        }
        val jarEntries = jarFile.entries().toList()
        //Instrumentation
        val entriesSize = jarEntries.size
        var lineUID = 0
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
            val lines = methods.flatMap { it.instructions.toList().filterIsInstance<LineNumberNode>() }.map { it.line }
            for (m in methods) {
                val instList = m.instructions.toList()
                for (instruction in instList) {
                    if (instruction !is LineNumberNode) continue
                    val lineNumber = instruction.line - 1
                    if (lineNumber !in targetCoverageEntry.second) continue
                    instrumentedCode.add(Pair(targetCoverageEntry.first, lineNumber) to lineUID)
                    listOf(
                        FieldInsnNode(Opcodes.GETSTATIC, "coverage/MyLineBasedCoverage", "coveredLines", "[I"),
                        IntInsnNode(Opcodes.SIPUSH, lineUID++),
                        InsnNode(Opcodes.ICONST_1),
                        InsnNode(Opcodes.IASTORE)
                    ).reversed().forEach { m.instructions.insert(instruction, it) }
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
        println("FINAL LINE = $lineUID")
        return instrumentedCode
    }
}