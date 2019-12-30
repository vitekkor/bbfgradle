package com.stepanov.bbf.bugfinder.manager

import com.stepanov.bbf.bugfinder.duplicates.util.MutationSequence
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.MutationChecker
import com.stepanov.bbf.bugfinder.util.MutationSaver
import com.stepanov.bbf.bugfinder.util.getRandomVariableName
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File
import java.util.*


//TODO Maybe add crashing message in comments
object FileReporter : Reporter {

    private fun dumpMutationSeq(path: String) {
        val json = Json(JsonConfiguration.Stable)
        val jsonData = json.stringify(MutationSaver.serializer(), MutationChecker.myMutSeq)
        File(path).writeText(jsonData)
    }

    fun saveRegularBug(bug: Bug) {
        val compilerBugDir = bug.compilerVersion.filter { it != ' ' }
        val resDir = CompilerArgs.resultsDir
        val randomName = Random().getRandomVariableName(5)
        val newPath =
                if (resDir.endsWith('/')) "$resDir$compilerBugDir/${bug.type.name}_$randomName.kt"
                else "$resDir/$compilerBugDir/${bug.type.name}_$randomName.kt"
        File(newPath).writeText(bug.crashingCode)
        //Dump JSON with mutation sequence for duplicates filtering
        dumpMutationSeq("${newPath.dropLast(3)}2.json")
    }

    fun saveDiffBug(bug: Bug, type: String) {
        val resDir = CompilerArgs.resultsDir
        val newPath =
                if (resDir.endsWith('/')) "${resDir}diff$type/${Random().getRandomVariableName(7)}.kt"
                else "${resDir}/diff$type/${Random().getRandomVariableName(7)}.kt"
        val diffCompilers = "// Different ${type.toLowerCase()} happens on:${bug.compilerVersion}"
        File(newPath.substringBeforeLast('/')).mkdirs()
        File(newPath).writeText("$diffCompilers\n${bug.crashingCode}")
        //Dump JSON with mutation sequence for duplicates filtering
        dumpMutationSeq("${newPath.dropLast(3)}2.json")
    }

    override fun dump(bugs: List<Bug>) {
        for (bug in bugs) {
            when (bug.type) {
                BugType.DIFFBEHAVIOR -> saveDiffBug(
                    bug,
                    "Behavior"
                )
                BugType.DIFFCOMPILE -> saveDiffBug(
                    bug,
                    "Compile"
                )
                else -> saveRegularBug(bug)
            }
        }
    }

}