package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.bugfinder.Reducer
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.executor.*
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.util.FilterDuplcatesCompilerErrors
import com.stepanov.bbf.bugfinder.util.StatisticCollector
import org.apache.log4j.Logger
import java.io.File

enum class BugType {
    BACKEND,
    FRONTEND,
    DIFFBEHAVIOR,
    UNKNOWN,
    DIFFCOMPILE,
    DIFFABI,
    PERFORMANCE
}

data class Bug(val compilers: List<CommonCompiler>, val msg: String, val crashedProject: Project, val type: BugType) {

    constructor(b: Bug) : this(
        b.compilers,
        b.msg,
        b.crashedProject.copy(),
        b.type
    )

    constructor(compiler: CommonCompiler, msg: String, crashedProject: Project, type: BugType) : this(
        listOf(compiler),
        msg,
        crashedProject,
        type
    )

    val compilerVersion = compilers.joinToString(", ")

    fun compareTo(other: Bug): Int =
        if (compilerVersion == other.compilerVersion)
            type.compareTo(other.type)
        else compilerVersion.compareTo(other.compilerVersion)

    fun getDirWithSameTypeBugs(): String =
        CompilerArgs.resultsDir.removeSuffix("/").plus("/") +
                when (type) {
                    BugType.DIFFBEHAVIOR -> "diffBehavior"
                    BugType.DIFFCOMPILE -> "diffCompile"
                    BugType.DIFFABI -> "diffABI"
                    BugType.FRONTEND, BugType.BACKEND -> compilers.first().compilerInfo.filter { it != ' ' }
                    else -> ""
                }

    fun copy() = Bug(compilers, msg, crashedProject.copy(), type)

    override fun toString(): String {
        return "${type.name}\n${compilers.map { it.compilerInfo }}\nText:\n${crashedProject}"
    }

    override fun equals(other: Any?): Boolean =
        other is Bug && other.crashedProject == this.crashedProject && other.type == this.type &&
                other.compilers.map { it.compilerInfo } == this.compilers.map { it.compilerInfo }

    override fun hashCode(): Int {
        var result = compilers.hashCode()
        result = 31 * result + msg.hashCode()
        result = 31 * result + crashedProject.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + compilerVersion.hashCode()
        return result
    }

}


object BugManager {
    private val bugs = mutableListOf<Bug>()

    fun saveBug(
        compilers: List<CommonCompiler>,
        msg: String,
        crashedProject: Project,
        type: BugType = BugType.UNKNOWN
    ) {
        val bug =
            if (type == BugType.UNKNOWN)
                Bug(
                    compilers,
                    msg,
                    crashedProject,
                    parseTypeOfBugByMsg(msg)
                )
            else
                Bug(compilers, msg, crashedProject, type)
        saveBug(bug)
    }

//    private fun checkIfBugIsProject(bug: Bug): Bug =
//        if (bug.crashedProject.files.size > 1) {
//            val checker =
//                CompilationChecker(bug.compilers)
//            if (bug.crashedProject.language == LANGUAGE.KOTLIN) {
//                val oneFileBugs = checker.checkAndGetCompilerBugs(bug.crashedProject.moveAllCodeInOneFile())
//                if (oneFileBugs.isNotEmpty()) Bug(
//                    bug.compilers,
//                    bug.msg,
//                    bug.crashedProject.moveAllCodeInOneFile(),
//                    bug.type
//                )
//                else bug
//            } else {
//                val text = bug.crashedProject.files.map { it.psiFile.text }.joinToString("\n")
//                if (checker.checkAndGetCompilerBugs(Project.createFromCode(text)).isNotEmpty())
//                    Bug(
//                        bug.compilers,
//                        bug.msg,
//                        Project.createFromCode(text),
//                        bug.type
//                    )
//                else bug
//            }
//        } else bug


    fun saveBug(bug: Bug) {
        try {
            val field = when (bug.type) {
                BugType.BACKEND -> "Backend"
                BugType.FRONTEND -> "Frontend"
                BugType.DIFFBEHAVIOR -> "Miscompilation"
                else -> ""
            }
            if (field.isNotEmpty()) StatisticCollector.incField(field)
            log.info("SAVING ${bug.type} BUG")
            println("SAVING ${bug.type} BUG")
            if (ReportProperties.getPropAsBoolean("SAVE_STATS") == true) saveStats()

            var originalBug: Bug? = null
            val newBug = if (bug.type == BugType.DIFFBEHAVIOR) {
                val project = bug.crashedProject
                val targetProject = project.copy(files = project.files.drop(1).map(BBFFile::copy))
                val originalProject = project.copy(files = project.files.take(1).map(BBFFile::copy))
                originalBug = bug.copy(crashedProject = originalProject)
                bug.copy(crashedProject = targetProject)
            } else bug.copy()
            log.debug("Start to reduce ${newBug.crashedProject}")
            val reduced =
                try {
                    Reducer.reduce(newBug)
                } catch (e: Exception) {
                    newBug.crashedProject.copy()
                }
            val reducedBug = Bug(newBug.compilers, newBug.msg, reduced, newBug.type)
            log.debug("Reduced: ${reducedBug.crashedProject}")
            val newestBug = reducedBug//checkIfBugIsProject(reducedBug)
            //Try to find duplicates
            if (/*newBug.crashedProject.texts.size == 1 &&*/
                CompilerArgs.shouldFilterDuplicateCompilerBugs &&
                haveDuplicates(newestBug)
            ) {
                StatisticCollector.incField("Duplicates")
                return
            }
            bugs.add(newestBug)
            //Report bugs
            if (ReportProperties.getPropAsBoolean("TEXT_REPORTER") == true) {
                TextReporter.dump(bugs)
            }
            if (ReportProperties.getPropAsBoolean("FILE_REPORTER") == true) {
                val bugList = when(bug.type) {
                    BugType.FRONTEND, BugType.BACKEND -> listOf(bug, newestBug)
                    BugType.DIFFBEHAVIOR -> listOf(originalBug!!, newestBug)
                    else -> listOf(newestBug)
                }
                FileReporter.dump(bugList)
            }
        } catch (e: Exception) {
            log.debug("Exception ${e.localizedMessage} ${e.stackTraceToString()}\n")
            System.exit(1)
        }
    }

    fun haveDuplicates(bug: Bug): Boolean {
        val dirWithSameBugs = bug.getDirWithSameTypeBugs()
        return when (bug.type) {
            BugType.DIFFCOMPILE -> FilterDuplcatesCompilerErrors.haveSameDiffCompileErrors(
                bug.crashedProject,
                dirWithSameBugs,
                bug.compilers,
                true
            )
            BugType.FRONTEND, BugType.BACKEND -> FilterDuplcatesCompilerErrors.simpleHaveDuplicatesErrors(
                bug.crashedProject,
                dirWithSameBugs,
                bug.compilers.first()
            )
            BugType.DIFFABI -> FilterDuplcatesCompilerErrors.haveSameDiffABIErrors(bug)
            BugType.DIFFBEHAVIOR -> false
            BugType.UNKNOWN -> false
            BugType.PERFORMANCE -> false
        }
    }

    private fun parseTypeOfBugByMsg(msg: String): BugType =
        if (msg.contains("Exception while analyzing expression"))
            BugType.FRONTEND
        else
            BugType.BACKEND

    private fun saveStats() {
        val f = File("bugsPerMinute.txt")
        val curText = StringBuilder(f.readText())
        val bugs = curText.split("\n").first().split(": ").last().toInt()
        val newText = curText.replaceFirst(Regex("\\d+\n"), "${bugs + 1}\n")
        f.writeText(newText)
    }

    private val log = Logger.getLogger("bugFinderLogger")
}

