package com.stepanov.bbf.bugfinder.executor.checkers

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.COMPILE_STATUS
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.manager.BugType
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.bugfinder.tracer.Tracer
import com.stepanov.bbf.bugfinder.util.statistic.LineCoverageStatisticsCollector
import com.stepanov.bbf.bugfinder.util.statistic.StatisticCollector
import com.stepanov.bbf.bugfinder.util.instrumentation.CoverageGuidingCoefficients
import com.stepanov.bbf.bugfinder.util.statistic.CoverageStatisticWriter
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import kotlinx.serialization.ExperimentalSerializationApi
import org.apache.logging.log4j.LogManager
import kotlin.math.exp
import kotlin.random.Random

//Project adaptation
@ExperimentalSerializationApi
open class CompilationChecker(private val compilers: List<CommonCompiler>) {

    constructor(compiler: CommonCompiler) : this(listOf(compiler))

    //Back compatibility
    fun checkTextCompiling(text: String): Boolean = checkCompilingWithBugSaving(Project.createFromCode(text), null)
    fun checkCompilingWithBugSaving(file: PsiFile): Boolean = checkTextCompiling(file.text)


    private fun createPsiAndCheckOnErrors(text: String, language: LANGUAGE): Boolean =
        when (language) {
            LANGUAGE.JAVA -> PSICreator.getPsiForJava(text, Factory.file.project)
            else -> Factory.psiFactory.createFile(text)
        }.let { tree ->
            tree.getAllPSIChildrenOfType<PsiErrorElement>().isEmpty() && additionalConditions.all { it.invoke(tree) }
        }

    //FALSE IF ERROR
    private fun checkSyntaxCorrectnessAndAddCond(project: Project, curFile: BBFFile?) =
        curFile?.let {
            createPsiAndCheckOnErrors(curFile.text, curFile.getLanguage())
        } ?: project.files.any { createPsiAndCheckOnErrors(it.text, it.getLanguage()) }


    fun checkCompiling(project: Project): Boolean {
        val allTexts = project.files.map { it.psiFile.text }.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        if (!checkSyntaxCorrectnessAndAddCond(project, null)) {
            log.debug("Wrong syntax or breaks conditions")
            checkedConfigurations[allTexts] = false
            return false
        }
        val statuses = compileAndGetStatuses(project)
        return if (statuses.all { it == COMPILE_STATUS.OK }) {
            checkedConfigurations[allTexts] = true
            true
        } else {
            checkedConfigurations[allTexts] = false
            false
        }
    }

    fun checkCompilingWithBugSaving(project: Project, curFile: BBFFile? = null): Boolean {
        log.debug("Compilation checking started")
        val allTexts = project.files.map { it.psiFile.text }.joinToString()
        checkedConfigurations[allTexts]?.let { log.debug("Already checked"); return it }
        //Checking syntax correction
        if (!checkSyntaxCorrectnessAndAddCond(project, curFile)) {
            log.debug("Wrong syntax or breaks conditions")
            StatisticCollector.incField("Incorrect programs")
            checkedConfigurations[allTexts] = false
            return false
        }
        val (statuses, compilationTimes) =
            compileAndGetStatusesWithExecutionTime(project).let { it.map { it.first } to it.map { it.second } }
        when {
            statuses.all { it == COMPILE_STATUS.OK } -> {
                if (CompilerArgs.isGuidedByCoverage && CompilerArgs.isMutationGuided) {
                    if (isCoverageDecreases(project)) {
                        checkedConfigurations[allTexts] = false
                        return false
                    }
                } else if (CompilerArgs.isGuidedByCoverage) {
                    val oldCoverage = CoverageStatisticWriter.instance.getPercentageOfDesiredCoverage()
                    val sumCoverage = LineCoverageGuider.getLineCoverage(project, compilers)
                    CoverageStatisticWriter.instance.addCoveredMethods(sumCoverage)
                    val newCoverage = CoverageStatisticWriter.instance.getPercentageOfDesiredCoverage()
                    if (newCoverage > oldCoverage) {
                        CoverageStatisticWriter.instance.addInformationAboutMutationCoverage(newCoverage - oldCoverage)
                    }
                }
                if (CompilerArgs.isMiscompilationMode) {
                    val checkRes = checkTraces(project)
                    checkedConfigurations[allTexts] = checkRes
                    return checkRes
                }
                StatisticCollector.incField("Correct programs")
                checkedConfigurations[allTexts] = true
                return true
            }
            statuses.all { it == COMPILE_STATUS.ERROR } -> {
                StatisticCollector.incField("Incorrect programs")
                checkedConfigurations[allTexts] = false
                return false
            }
        }
        //TODO!!
        checkAndGetCompilerBugs(project).forEach {
            if (BugManager.saveBug(it) && CompilerArgs.isGuidedByCoverage) {
                currentScore += CoverageGuidingCoefficients.SCORES_FOR_BUG
            }
        }
        checkedConfigurations[allTexts] = false
        StatisticCollector.incField("Correct programs")
        return false
    }

    private fun compileAndGetStatuses(project: Project): List<COMPILE_STATUS> =
        compilers.map { it.tryToCompileWithStatus(project) }

    private fun compileAndGetStatusesWithExecutionTime(project: Project): List<Pair<COMPILE_STATUS, Long>> =
        compilers.map { it.tryToCompileWithStatusAndExecutionTime(project) }


    private fun checkTraces(project: Project): Boolean {
        val copyOfProject = project.copy()
        Tracer(compilers.first(), copyOfProject).trace()
        return when (TracesChecker(compilers).checkBehavior(copyOfProject)) {
            CHECKRES.BUG -> false.also { currentScore += CHECKRES.BUG.scoreForBug }
            CHECKRES.NOT_OK -> false
            CHECKRES.OK -> true
        }
    }

    private fun decrementKoef(oldKoef: Double): Double {
        var multKoef = 0.1
        while (true) {
            if (oldKoef - multKoef > 0.0) {
                return oldKoef - multKoef
            } else {
                multKoef /= 10.0
            }
        }
    }

    //Is coverage decreasing?
    private fun isCoverageDecreases(project: Project): Boolean {
        println("---")
        val previousCoveredPercentage = CoverageStatisticWriter.instance.getPercentageOfDesiredCoverage()
        val sumCoverage = LineCoverageGuider.getLineCoverage(project, compilers)
        CoverageStatisticWriter.instance.addCoveredMethods(sumCoverage)
        val k = LineCoverageGuider.calcKoefOfCoverageUsage(sumCoverage)
        println("K = $k")
        val newCoveragePercentage = CoverageStatisticWriter.instance.getPercentageOfDesiredCoverage()
        if (unsuccessfulMutations > 5) {
            acceptanceCoef = decrementKoef(acceptanceCoef)
        }
        val probabilityOfAcceptance =
            minOf(1.0, exp(acceptanceCoef * (k - LineCoverageGuider.initCoef)))
        val isDecreasing = Random.nextDouble(0.0, 1.0) > probabilityOfAcceptance
        println("Probability = $probabilityOfAcceptance")
        println("Coverage diff = ${k - LineCoverageGuider.initCoef}")
        println("is Accepting = ${!isDecreasing}")
        if (newCoveragePercentage > previousCoveredPercentage) {
            CoverageStatisticWriter.instance.addInformationAboutMutationCoverage(newCoveragePercentage - previousCoveredPercentage)
        }
        if (!isDecreasing) {
            currentScore += (k - LineCoverageGuider.initCoef).let {
                when {
                    it <= 0 -> 0
                    else -> it
                }
            }
//                if (it < 0)
//                    0 else it }
            LineCoverageGuider.initCoef = k
            unsuccessfulMutations = 0
            acceptanceCoef = CoverageGuidingCoefficients.MCMC_MULTIPLIER
        } else {
            println("unsuccessfulMutations in a row = $unsuccessfulMutations")
            unsuccessfulMutations++
        }
        println("CURRENT SCORE = $currentScore")
        println("---")
        return isDecreasing
    }

    private fun checkAndGetCompilerBugs(project: Project): List<Bug> {
        val res = mutableListOf<Bug>()
        compilers.forEach { compiler ->
            if (compiler.isCompilerBug(project)) {
                val msg = compiler.getErrorMessage(project)
                val type =
                    if (msg.contains("Exception while analyzing expression")) BugType.FRONTEND else BugType.BACKEND
                res.add(Bug(compiler, msg, project.copy(), type))
            }
        }
        if (res.size != 0) return res
        val compilersToStatus = compilers.map { it to it.checkCompiling(project) }
        val grouped = compilersToStatus.groupBy { it.first.compilerInfo.split(" ").first() }
        for (g in grouped) {
            if (g.value.map { it.second }.toSet().size != 1) {
                val diffCompilers =
                    g.value.groupBy { it.second }.mapValues { it.value.first().first }.values.toList()
                res.add(
                    Bug(diffCompilers, "", project.copy(), BugType.DIFFCOMPILE)
                )
            }
        }
        return res
    }

//        fun checkABI(project: Project): Pair<Int, File>? {
//            val compilers = CompilerArgs.getCompilersList()
//            if (compilers.size != 2) return null
//            val compiled = compilers.mapIndexed { index, comp ->
//                comp.pathToCompiled =
//                    comp.pathToCompiled.replace(".jar", "$index.jar")
//                comp.compile(project).pathToCompiled
//            }
//            if (compiled.any { it == "" }) return null
//            val jars = compiled.map { JarFile(it) }
//            val task =
//                JarTask(
//                    "",
//                    jars.first(),
//                    jars.last(),
//                    compilers.first().compilerInfo,
//                    compilers.last().compilerInfo,
//                    File("tmp/report.html"),
//                    checkerConfiguration {}
//                )
//            val execRes = task.execute()
//            MySummaryReport.summary.add(task.defectReport)
//            return execRes
//        }

    val additionalConditions: MutableList<(PsiFile) -> Boolean> = mutableListOf()
    var currentScore = 0
    private var unsuccessfulMutations = 0
    private var acceptanceCoef = CoverageGuidingCoefficients.MCMC_MULTIPLIER

    private val checkedConfigurations = hashMapOf<String, Boolean>()
    private val log = LogManager.getLogger("mutatorLogger")
}