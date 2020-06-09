package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.util.Stream
import com.stepanov.bbf.bugfinder.util.checkCompilingForAllBackends
import org.apache.log4j.Logger

// Transformation is here only for PSIFactory
class TracesChecker(compilers: List<CommonCompiler>) : CompilationChecker(compilers) {

    private companion object FalsePositivesTemplates {
        //Regex and replacing
        val exclErrorMessages = listOf(
            "IndexOutOfBoundsException",
            "ArithmeticException",
            "KotlinReflectionInternalError" //TODO!!
        )
    }

//    fun checkTest(text: String): List<CommonCompiler>? {
//        var resText = text
//        if (!resText.contains("fun main(")) {
//            resText += "fun main(args: Array<String>) {\n" +
//                    "    println(box())\n" +
//                    "}"
//        }
//        val writer = BufferedWriter(FileWriter(CompilerArgs.pathToTmpFile))
//        writer.write(resText)
//        writer.close()
//        val res = checkTest(resText, CompilerArgs.pathToTmpFile)
//        File(CompilerArgs.pathToTmpFile).delete()
//        return res
//    }

//    fun addMainForKJavaProject(project: Project): Nothing = TODO()
//        Project(project.texts
//            .map { it to it.getFileLanguageIfExist() }
//            .map { if (it.second == LANGUAGE.KOTLIN) it.first to psiFactory.createFile(it.first) else it.first to null }
//            .map {
//                if (it.second?.getAllPSIChildrenOfType<KtNamedFunction>()
//                        ?.any { it.name?.contains("box") == true } == true
//                ) addMain(it.first) else
//                    it.first
//            }, null, LANGUAGE.KJAVA
//        )


//    fun addMainForProject(project: Project): Project = TODO()
//    {
//        if (project.language == LANGUAGE.KJAVA) return addMainForKJavaProject(project)
//        if (project.texts.size == 1) {
//            val newText = addMain(project.texts.first())
//            return Project(listOf(newText))
//        } else {
//            val files = project.texts.map { psiFactory.createFile(it) }
//            val boxFuncs = files.map { file ->
//                file.getAllPSIChildrenOfType<KtNamedFunction>().find { it.name?.contains("box") ?: false }!!
//            }
//            //Add import of box_I functions
//            val firstFile = files.first()
//            boxFuncs.forEachIndexed { i, func ->
//                val `package` = (func.parents.find { it is KtFile } as KtFile).packageDirective?.fqName
//                    ?: throw IllegalArgumentException("No package")
//                val newImport =
//                    psiFactory.createImportDirective(ImportPath(FqName("${`package`}.${func.name}"), false))
//                firstFile.addImport(newImport)
//            }
//            firstFile.addMain(boxFuncs)
//            return Project(files)
//        }
//    }

//    fun compareTraces(project: Project): List<CommonCompiler>? = TODO()
//    {
//        val path = project.generateCommonName()
//        //Check if already checked
//        val text = project.getCommonText(path)
//        val hash = text.hashCode()
//        if (alreadyChecked.containsKey(hash)) {
//            log.debug("ALREADY CHECKED!!!")
//            return alreadyChecked[hash]!!
//        }
//
//        //Add main
//        val projectWithMain = addMainForProject(project)
//        if (!isCompilationSuccessful(projectWithMain)) {
//            log.debug("Cant compile with main")
//            log.debug("Proj = ${projectWithMain.getCommonTextWithDefaultPath()}")
//            return null
//        }
//        projectWithMain.saveOrRemoveToTmp(true)
//        val results = mutableListOf<Pair<CommonCompiler, String>>()
//        for (comp in compilers) {
//            val status = comp.compile(path)
//            if (status.status == -1) {
//                clean(projectWithMain, status.pathToCompiled)
//                return null
//            }
//            val res = comp.exec(status.pathToCompiled)
//            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
//            log.debug("Result of ${comp.compilerInfo}: $res\n")
//            log.debug("Errors: $errors")
//            if (exclErrorMessages.any { errors.contains(it) }) {
//                clean(projectWithMain, status.pathToCompiled)
//                return null
//            }
//            results.add(comp to res.trim())
//        }
//        clean(projectWithMain, null)
//        val groupedRes = results.groupBy({ it.second }, valueTransform = { it.first })
//        return if (groupedRes.size == 1) {
//            null
//        } else {
//            val res = groupedRes.map { it.value.first() }
//            alreadyChecked[hash] = res
//            res
//        }
//    }


//    fun checkTestForProject(commonPath: String): List<CommonCompiler>? = TODO()
//    {
//        val results = mutableListOf<Pair<CommonCompiler, String>>()
//        for (comp in compilers) {
//            val status = comp.compile(commonPath)
//            if (status.status == -1)
//                return null
//            val res = comp.exec(status.pathToCompiled)
//            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
//            log.debug("Result of ${comp.compilerInfo}: $res\n")
//            log.debug("Errors: $errors")
//            results.add(comp to res.trim())
//        }
//        val groupedRes = results.groupBy({ it.second }, valueTransform = { it.first })
//        return if (groupedRes.size == 1) {
//            null
//        } else {
//            groupedRes.map { it.value.first() }
//        }
//    }

    fun checkTest(project: Project) {
        log.debug("Trying to compile with main function:")
        val extendedCompilerList = compilers + listOf(JVMCompiler("-Xno-optimize"))
        if (!extendedCompilerList.checkCompilingForAllBackends(project)) {
            log.debug("Cannot compile with main")
            return
        }

        log.debug("Executing traced code:\n$project")
        val results = mutableListOf<Pair<CommonCompiler, String>>()
        for (comp in extendedCompilerList) {
            val status = comp.compile(project)
            if (status.status == -1)
                return
            val res = comp.exec(status.pathToCompiled)
            val errors = comp.exec(status.pathToCompiled, Stream.ERROR)
            log.debug("Result of ${comp.compilerInfo}: $res\n")
            log.debug("Errors: $errors")
            if (exclErrorMessages.any { errors.contains(it) })
                return
            results.add(comp to res.trim())
        }
//        //Compare with java
//        if (CompilerArgs.useJavaAsOracle) {
//            try {
//                val res = JCompiler().compile(pathToFile)
//                if (res.status == 0) {
//                    val execRes = JCompiler().exec(res.pathToCompiled, Stream.BOTH)
//                    log.debug("Result of JAVA: $execRes")
//                    results.add(JCompiler() to execRes.trim())
//                } else log.debug("Cant compile with Java")
//            } catch (e: Exception) {
//                log.debug("Exception with Java compilation")
//            }
//        }
        val groupedRes = results.groupBy({ it.second }, valueTransform = { it.first }).toMutableMap()
        if (groupedRes.size != 1) {
            BugManager.saveBug(
                Bug(
                    groupedRes.map { it.value.first() },
                    "",
                    project,
                    BugType.DIFFBEHAVIOR
                )
            )
        }
    }

    private val log = Logger.getLogger("bugFinderLogger")
}
