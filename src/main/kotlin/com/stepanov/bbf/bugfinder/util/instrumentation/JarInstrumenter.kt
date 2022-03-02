package com.stepanov.bbf.bugfinder.util.instrumentation

import coverage.CoverageEntry
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.*
import org.objectweb.asm.util.CheckClassAdapter
import java.io.*
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream

open class JarInstrumenter {

    protected fun addEntry(entry: JarEntry, jarFile: JarFile, newJarStream: JarOutputStream) {
        val jarEntry = JarEntry(entry)
        jarEntry.compressedSize = -1
        newJarStream.putNextEntry(jarEntry)
        val inputStream = jarFile.getInputStream(entry)
        val byteArray = inputStream.readAllBytes()
        newJarStream.write(byteArray)
        newJarStream.closeEntry()
        newJarStream.closeEntry()
    }

    private val READ_BUFFER_SIZE = 8 * 1024

    private fun copyStream(input: InputStream, output: OutputStream) {
        val buffer = ByteArray(READ_BUFFER_SIZE)
        while (true) {
            val count = input.read(buffer)
            if (count == -1) {
                break
            }
            output.write(buffer, 0, count)
        }
    }

    protected fun addFileToJar(jar: JarOutputStream, source: File, entryName: String?) {
        BufferedInputStream(FileInputStream(source)).use { `in` ->
            val entry = JarEntry(entryName)
            entry.time = source.lastModified()
            jar.putNextEntry(entry)
            copyStream(`in`, jar)
            jar.closeEntry()
        }
    }

    protected val abstractOpcodeLength = Opcodes.ACC_ABSTRACT.toString(2).length

    private fun writeCoverageClasses(jarOutputStream: JarOutputStream, files: List<String>) {
        files
            .map { File(it) }
            .forEach {
                val name = "coverage/${it.name.substringAfterLast('/')}"
                addFileToJar(jarOutputStream, it, name)
            }
    }

    protected fun writeLineCoverageClassesToJar(jarOutputStream: JarOutputStream) =
        writeCoverageClasses(
            jarOutputStream,
            listOf("tmp/lib/MyLineBasedCoverage.class")
        )

    protected fun writeBranchCoverageClassesToJar(jarOutputStream: JarOutputStream) =
        writeCoverageClasses(
            jarOutputStream,
            listOf(
                "tmp/lib/BranchCoverageEntry.class",
                "tmp/lib/MyBranchBasedCoverage.class"
            )
        )

    protected fun writeCoverageClassesToJar(jarOutputStream: JarOutputStream) =
        writeCoverageClasses(
            jarOutputStream,
            listOf(
                "tmp/lib/CoverageEntry.class",
                "tmp/lib/CoverageEntry\$Companion.class",
                "tmp/lib/MyMethodBasedCoverage.class"
            )
        )


    open fun instrument(jarPath: String, newJarPath: String, targetCoverage: Map<CoverageEntry, Int>) {
        if (File(newJarPath).exists()) File(newJarPath).delete()
        val jarFile = JarFile(jarPath)

        //Add data structures to collect traces
        val outputStream = JarOutputStream(FileOutputStream(File(newJarPath)))
        listOf(
            "tmp/lib/CoverageEntry.class",
            "tmp/lib/CoverageEntry\$Companion.class",
            "tmp/lib/MyMethodBasedCoverage.class"
        )
            .map { File(it) }
            .forEach {
                val name = "coverage/${it.name.substringAfterLast('/')}"
                addFileToJar(outputStream, it, name)
            }

        val jarEntries = jarFile.entries().toList()
        val entriesToInstrument =
            if (targetCoverage.isNotEmpty()) {
                val classNames = targetCoverage.keys.toList().map { it.pathToFun.substringAfterLast('.') }.toSet()
                classNames.flatMap { className -> jarEntries.filter { it.name.contains(className, true) } }.toSet()
            } else {
                jarEntries
            }.toHashSet()
        //Instrumentation
        for (entry in jarEntries) {
            val className = entry.realName.substringBeforeLast(".class").replace('/', '.')
            if (!entry.name.endsWith(".class")
                || entry.name.startsWith("META-INF")
                || entry.name.startsWith("coverage")
                || entry !in entriesToInstrument
            ) {
                addEntry(entry, jarFile, outputStream)
                continue
            }
            val reader = ClassReader(jarFile.getInputStream(entry))
            val classNode = ClassNode()
            reader.accept(classNode, 0)
            val methods = classNode.methods
            for (m in methods) {
                val accessAsBinary = m.access.toString(2).reversed()
                //We cannot insert code in abstract methods isAbstract
                if (accessAsBinary.length >= abstractOpcodeLength && accessAsBinary[abstractOpcodeLength - 1] == '1') continue
                val instructions = m.instructions
                val (path, name, param, returnType) = m.getCoverageEntry(className)
                val newInstructionList = listOf(
                    TypeInsnNode(Opcodes.NEW, "coverage/CoverageEntry"),
                    InsnNode(Opcodes.DUP),
                    LdcInsnNode(path),
                    LdcInsnNode(name),
                    LdcInsnNode(param),
                    LdcInsnNode(returnType),
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
    }
}