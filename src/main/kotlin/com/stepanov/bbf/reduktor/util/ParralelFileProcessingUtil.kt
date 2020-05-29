package com.stepanov.bbf.reduktor.util

import com.stepanov.bbf.reduktor.executor.CommonCompilerCrashTestChecker
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.executor.backends.CommonBackend
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.manager.TransformationManager
import org.apache.log4j.Logger
import org.codehaus.plexus.util.FileUtils
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

enum class TaskType {
    TRANSFORM, SIMPLIFYING
}

fun createNewProjectCopy(id: Int, projPath: String): String {
    val newPath = projPath + id
    FileUtils.copyDirectoryStructure(File(projPath), File(newPath))
    return newPath
}

fun createTask(reduceFile: KtFile, index: Int, projPath: String, type: TaskType, checker: CompilerTestChecker): Callable<KtFile> {
    val newPath = createNewProjectCopy(index, projPath)
    val newCreator = PSICreator(newPath)
    val newFiles = newCreator.getPSI()
    val bugFileName = reduceFile.name.drop(newPath.length - index.toString().length)
    var newFileWithBug = reduceFile
    for (f in newFiles) {
        val cutName = f.name.drop(newPath.length)
        if (cutName == bugFileName) {
            newFileWithBug = f
            break
        }
    }
    return when (type) {
        TaskType.TRANSFORM -> ParallelTransform(
            newFileWithBug,
            newPath,
            checker
        )
        TaskType.SIMPLIFYING -> ParallelFuncSimplifier(
            newFileWithBug,
            newPath,
            checker
        )
    }

}

fun startTasksAndSaveNewFiles(ktFiles: List<KtFile>, projPath: String, type: TaskType, checker: CompilerTestChecker) {
    val procs = Runtime.getRuntime().availableProcessors() - 1
    val executor = Executors.newFixedThreadPool(procs)
    val taskList = mutableListOf<Callable<KtFile>>()
    for ((i, reduceFile) in ktFiles.take(procs).withIndex()) {
        taskList.add(createTask(reduceFile, i, projPath, type, checker))
    }
    val res = mutableListOf<Future<KtFile>>()
    for (task in taskList) {
        res.add(executor.submit(task))
    }
    var i = procs
    while (i < ktFiles.size) {
        val readyFile = res.find { it.isDone }
        readyFile?.let {
            val file = it.get()
            val index = res.indexOf(readyFile)
            //Save ready file and spawn new task
            saveFile(file, projPath)
            //Spawn
            res[index] = (executor.submit(
                createTask(
                    ktFiles[i],
                    i,
                    projPath,
                    type,
                    checker
                )
            ))
            ++i
        }
    }
    while (!res.all { it.isDone }) {
    }
    res.map { it.get() }.forEach { saveFile(it, projPath) }
}

private fun saveFile(file: KtFile, projPath: String) {
    val path = file.name.take(projPath.length)
    val end = file.name.substring(projPath.length).dropWhile { it != '/' }
    val oldPath = file.name.take(projPath.length) + file.name.substring(projPath.length).takeWhile { it != '/' }
    file.name = path + end
    val newFile = File(file.name)
    val writer = newFile.bufferedWriter()
    writer.write(file.text)
    writer.close()
    FileUtils.deleteDirectory(oldPath)
}


private class ParallelTransform(private val fileToReduce: KtFile, private val path: String, private val checker: CompilerTestChecker) : Callable<KtFile> {
    override fun call(): KtFile = TODO()
//    {
//        val manager = TransformationManager(listOf(fileToReduce to fileToReduce.name))
//        val newFile = manager.doForParallelSimpleTransformations(true, path, checker)
//        return newFile!!
//    }
}

private class ParallelFuncSimplifier(private val fileToReduce: KtFile, val path: String, private val checker: CompilerTestChecker) : Callable<KtFile> {
    override fun call(): KtFile {
//        log.debug("HANDLE FILE {${fileToReduce.name}")
//        checker.pathToFile = fileToReduce.name
//        checker.init(path, KtPsiFactory(fileToReduce))
//        checker.refreshAlreadyCheckedConfigurations()
//        checker.pathToFile = fileToReduce.name
//        log.debug("ERROR = ${checker.getErrorInfo()}")
//        val allFuncs = fileToReduce.getAllPSIChildrenOfType<KtNamedFunction>()
//        val funcCopies = allFuncs.map { it.copy() }
//        allFuncs
//                .sortedByDescending { it.textLength }
//                .filter { it.hasBlockBody() }
//                .forEach { it.initBodyByTODO(KtPsiFactory(fileToReduce.project)) }
//        val res = checker.checkTest(fileToReduce.text, fileToReduce.name)
//        log.debug("RES = $res")
//        if (!res) {
//            for ((i, func) in allFuncs.withIndex()) {
//                func.replaceThis(funcCopies[i])
//            }
//        }
        return fileToReduce
    }

    private val log = Logger.getLogger("transformationManagerLog")
}
