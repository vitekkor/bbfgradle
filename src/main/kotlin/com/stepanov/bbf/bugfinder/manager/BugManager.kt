package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.bugfinder.Reducer
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.Project

enum class BugType {
    BACKEND,
    FRONTEND,
    DIFFBEHAVIOR,
    UNKNOWN,
    DIFFCOMPILE
}

data class Bug(val compilers: List<CommonCompiler>, val msg: String, val crashedProject: Project, val type: BugType) {

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


    private fun saveBug(bug: Bug) {
        bugs.add(bug)
        val reduced = Reducer.reduce(bug, false)
        //Report bugs
        if (ReportProperties.getPropAsBoolean("TEXT_REPORTER") == true) {
            TextReporter.dump(bugs)
        }
        if (ReportProperties.getPropAsBoolean("FILE_REPORTER") == true) {
            FileReporter.dump(listOf(bug))
        }
    }

    private fun parseTypeOfBugByMsg(msg: String): BugType =
        if (msg.contains("Exception while analyzing expression"))
            BugType.FRONTEND
        else
            BugType.BACKEND
}

