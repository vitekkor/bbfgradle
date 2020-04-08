package com.stepanov.bbf.reduktor.manager

import com.intellij.psi.PsiFile
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.executor.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.Project
import com.stepanov.bbf.bugfinder.executor.ProjectMultiCompilerTestChecker
import com.stepanov.bbf.bugfinder.util.getFileLanguageIfExist
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.passes.*
import com.stepanov.bbf.reduktor.passes.slicer.Slicer
import com.stepanov.bbf.reduktor.util.*
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import java.io.File
import java.io.PrintWriter

class TransformationManager(private val psiFiles: List<Pair<PsiFile, String>>) {

    var ktFactory: KtPsiFactory? = null
    var context: BindingContext? = null
    private val log = Logger.getLogger("transformationManagerLog")

    init {
        ktFactory = KtPsiFactory(psiFiles[0].first.project)
    }

    fun doProjectTransformations(
        targetFiles: List<Pair<PsiFile, String>>,
        checker: ProjectMultiCompilerTestChecker
    ): List<PsiFile> {
        val path = targetFiles.joinToString(" ") { it.second }
        val errorInfo = checker.init(path, KtPsiFactory(targetFiles[0].first))
        val res = mutableListOf<PsiFile>()
        for ((i, file) in targetFiles.map { it.first }.withIndex()) {
            //checker.otherFiles = Project(targetFiles.map { it.first.text }.getAllWithout(i))
            checker.otherFiles =
                Project(res.map { it.text } + targetFiles.map { it.first.text }.let { it.subList(i + 1, it.size) })
            checker.filePos = i
            if (file.text.getFileLanguageIfExist() == LANGUAGE.KOTLIN) {
                log.debug("Reducing of ${file.name} began")
                res.add(doTransformationsForFile(file as KtFile, checker))
            } else {
                log.debug("Reducing of ${file.name} began")
                val HDD = HierarchicalDeltaDebugger(file.node, checker)
                HDD.hdd()
                res.add(file.copy() as PsiFile)
            }
        }
        return res
//        if (errorInfo.type == ErrorType.UNKNOWN)
//            System.exit(0)
//        var file = targetFiles.find { it.second == Error.pathToFile }!!
//        if (file.second == Error.pathToFile) {
//            startTasksAndSaveNewFiles(
//                creator.targetFiles,
//                projectDir,
//                TaskType.SIMPLIFYING,
//                checker
//            )
//            creator.reinit(projectDir)
//            checker.reinit()
//            file = targetFiles.find { it.name == Error.pathToFile }!!
//            PreliminarySimplification(file, projectDir, checker).computeSlice(creator.targetFiles)
//            creator.reinit(projectDir)
//            checker.reinit()
//            file = creator.targetFiles.find { it.name == Error.pathToFile }!!
//            Slicer(file, checker).computeSlice(errorInfo.line, Slicer.Level.INTRAPROCEDURAL)
//            Slicer(file, checker).computeSlice(errorInfo.line, Slicer.Level.FUNCTION)
//            Slicer(file, checker).computeSlice(errorInfo.line, Slicer.Level.CLASS)
//            Save new file with error and reinit
//            val newFile = File(file.name)
//            val writer = newFile.bufferedWriter()
//            writer.write(file.text)
//            writer.close()
//            checker.reinit()
//            file = creator.targetFiles.find { it.name == Error.pathToFile }!!
//            RemoveInheritance(file, checker).transform(creator.targetFiles)
//            SimplifyInheritance(file, checker).transform(creator.targetFiles)
//            creator.reinit(debugProjectDir)
//            CCTC.reinit()
//            //Now transform file with bug
//            file = creator.targetFiles.find { it.name == Error.pathToFile }!!
//            val TM = TransformationManager(listOf(file))
//            val res = TM.doTransformationsForFile(file, checker, true, projectDir)
//            println("Res = ${res.text}")
//        }
        //Saving
//        for (f in newFiles) {
//            println("SAVING ${f.name}")
//            val directory = pathToSave + f.name.substring(debugProjectDir.length, f.name.indexOfLast { it == '/' })
//            val path = pathToSave + f.name.substring(debugProjectDir.length)
//            val newDirs = File(directory)
//            newDirs.mkdirs()
//            val newFile = File(path)
//            val writer = newFile.bufferedWriter()
//            writer.write(f.text)
//            writer.close()
//        }
    }


    fun doTransformationsForFile(
        file: KtFile, checker: CompilerTestChecker,
        isProject: Boolean = false, projectDir: String = ""
    ): KtFile {
        log.debug("FILE NAME = ${file.name}")
        log.debug("Content = ${file.text}")
        file.beforeAstChange()
        val pathToSave = StringBuilder(file.name)
        pathToSave.insert(pathToSave.indexOfLast { it == '/' }, "/minimized")
        checker.pathToFile = file.name
        var rFile = file.copy() as KtFile
        try {
            if (isProject)
                checker.init(projectDir, ktFactory!!)
            else
                checker.init(file.name, ktFactory!!)
        } catch (e: IllegalArgumentException) {
            return rFile
        }
        require(checker.checkTest(file.text)) { "No bug" }
        checker.refreshAlreadyCheckedConfigurations()
//        if (checker.getErrorMessage().contains("Unresolved") || checker.getErrorMessage().contains("Expecting")
//                || checker.getErrorMessage().isEmpty() || checker.getErrorInfo().type == ErrorType.UNKNOWN) {
//            return file
//        }
        log.debug("ERROR = ${checker.getErrorInfo()}")
        //log.debug("MSG = ${CCTC.getErrorInfo().errorMessage}")
//            if (CCTC.getErrorInfo().type == ErrorType.UNKNOWN)
//                continue
        var oldRes = file.text
//            DeleteComments(CCTC).transform(rFile)

        while (true) {
            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
                RemoveSuperTypeList(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY RemoveSuperTypeList = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER RemoveSuperTypeList ${rFile.text != oldRes}")
                SimplifyParameterList(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyParameterList = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyParameterList ${rFile.text != oldRes}")
                SimplifyContainerType(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyContainerType = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyContainerType ${rFile.text != oldRes}")
                SimplifyControlExpression(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyControlExpression = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyControlExpression ${rFile.text != oldRes}")
                SimplifyFunAndProp(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyFunAndProp = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyFunAndProp ${rFile.text != oldRes}")
                ReplaceBlockExpressionToBody(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY ReplaceBlockExpressionToBody = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER ReplaceBlockExpressionToBody ${rFile.text != oldRes}")
                SimplifyWhen(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyWhen = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyWhen ${rFile.text != oldRes}")
                ReturnValueToVoid(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY ReturnValueToVoid = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER ReturnValueToVoid ${rFile.text != oldRes}")
                ElvisOperatorSimplifier(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY ElvisOperatorSimplifier = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER ElvisOperatorSimplifier ${rFile.text != oldRes}")
                TryCatchDeleter(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY TryCatchDeleter = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER TryCatchDeleter ${rFile.text != oldRes}")
                SimplifyIf(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyIf = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyIf ${rFile.text != oldRes}")
                SimplifyFor(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyFor = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyFor ${rFile.text != oldRes}")
                //SimplifyLambdaExpression(rFile, CCTC).transform()
//                log.debug("VERIFY SimplifyLambdaExpression = ${checker.checkTest(rFile.text)}")
//                log.debug("CHANGES AFTER SimplifyLambdaExpression ${rFile.text != oldRes}")
                RemoveInheritance(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY RemoveInheritance = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER RemoveInheritance ${rFile.text != oldRes}")
                SimplifyInheritance(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyInheritance = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyInheritance ${rFile.text != oldRes}")
                SimplifyConstructor(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER SimplifyConstructor = ${rFile.text != oldRes}")
                log.debug("VERIFY TRYCATCH = ${checker.checkTest(rFile.text)}")
                ReturnValueToConstant(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER ReturnValueToConstant = ${rFile.text != oldRes}")
                SimplifyBlockExpression(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER SimplifyBlockExpression = ${rFile.text != oldRes}")
                log.debug("VERIFY SimplifyBlockExpression = ${checker.checkTest(rFile.text)}")
                SimplifyBinaryExpression(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER SimplifyBinaryExpression = ${rFile.text != oldRes}")
                log.debug("VERIFY SimplifyBinaryExpression = ${checker.checkTest(rFile.text)}")
                SimplifyStringConstants(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY SimplifyStringConstants = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER SimplifyStringConstants ${rFile.text != oldRes}")
            }
            if (ReduKtorProperties.getPropAsBoolean("SLICING") == true) {
                var errorInfo = checker.getErrorInfo()
                log.debug("ERROR INFO = $errorInfo")
                Slicer(rFile, checker)
                    .computeSlice(errorInfo.line, Slicer.Level.INTRAPROCEDURAL)
                log.debug("VERIFY INTREPR = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER INTREPR ${rFile.text != oldRes}")
                errorInfo = checker.reinit()
                Slicer(rFile, checker)
                    .computeSlice(errorInfo.line, Slicer.Level.FUNCTION)
                log.debug("VERIFY FUNC = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER FUNC ${rFile.text != oldRes}")
                errorInfo = checker.reinit()
                Slicer(rFile, checker)
                    .computeSlice(errorInfo.line, Slicer.Level.CLASS)
                log.debug("CHANGES AFTER CLASS ${rFile.text != oldRes}")
                log.debug("VERIFY CLASS = ${checker.checkTest(rFile.text)}")
            }
            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
//                //Get context
                File(rFile.name).writeText(rFile.text)
                val creator = PSICreator("")
                rFile = creator.getPSIForFile(rFile.name, true)
                if (creator.ctx != null) {
                    MinorSimplifyings(rFile, checker, creator.ctx!!).transform()
                    rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                    log.debug("VERIFY MinorSimplifyings = ${checker.checkTest(rFile.text)}")
                    log.debug("CHANGES AFTER MinorSimplifyings ${rFile.text != oldRes}")
                }
                RemoveParameterFromDeclaration(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY RemoveParameterFromDeclaration = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER RemoveParameterFromDeclaration ${rFile.text != oldRes}")
                FunInliner(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY FunInliner = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER FunInliner ${rFile.text != oldRes}")
                ReplaceArgOnTODO(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY ReplaceArgOnTODO = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER ReplaceArgOnTODO ${rFile.text != oldRes}")
                ValueArgumentListSimplifying(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY ValueArgumentListSimplifying = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER ValueArgumentListSimplifying ${rFile.text != oldRes}")
                val newText = PeepholePasses(rFile.text, checker, false).transform()
                log.debug("CHANGES AFTER PEEPHOLE ${newText != oldRes}")
                log.debug("VERIFY PEEPHOLE = ${checker.checkTest(newText)}")
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, newText)
                log.debug("UPDATED ${checker.checkTest(rFile.text)}")
                ConstructionsDeleter(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER EMPTY ${rFile.text != oldRes}")
                log.debug("VERIFY EMPTY = ${checker.checkTest(rFile.text)}")
            }
            if (ReduKtorProperties.getPropAsBoolean("FASTREDUCE") == true) {
                PSIReducer(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER FASTREDUCE ${rFile.text != oldRes}")
                log.debug("VERIFY FASTREDUCE = ${checker.checkTest(rFile.text)}")
            }
            if (ReduKtorProperties.getPropAsBoolean("HDD") == true) {
                val HDD = HierarchicalDeltaDebugger(rFile.node, checker)
                HDD.hdd()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER HDD ${rFile.text != oldRes}")
                log.debug("VERIFY HDD = ${checker.checkTest(rFile.text)}")
            }
            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
                EqualityMapper(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER EqualityMapper ${rFile.text != oldRes}")
                log.debug("VERIFY EqualityMapper = ${checker.checkTest(rFile.text)}")
                FunSimplifier(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("VERIFY FunSimplifier = ${checker.checkTest(rFile.text)}")
                log.debug("CHANGES AFTER FunSimplifier ${rFile.text != oldRes}")
                TypeChanger(rFile, checker).transform()
                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
                log.debug("CHANGES AFTER TypeChanger ${rFile.text != oldRes}")
                log.debug("VERIFY TypeChanger = ${checker.checkTest(rFile.text)}")
            }
            RemoveWhitespaces(rFile, checker).transform()
            rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
            log.debug("CURRENT RESULT = ${rFile.text}")
            if (rFile.text.filterNot { it.isWhitespace() } == oldRes.filterNot { it.isWhitespace() }) {
                //if (rFile.text.filterNot { it == '\n' } == oldRes.filterNot { it == '\n' }) {
                break
            }
            oldRes = rFile.text
        }
        log.debug("VERIFY = ${checker.checkTest(rFile.text)}")
        log.debug("RESULT: ${rFile.text}")
//        SAVING
        if (!isProject && ReduKtorProperties.getPropAsBoolean("SAVE_RESULT") == true) {
            File(pathToSave.toString().substringBeforeLast('/')).mkdirs()
            val writer = PrintWriter(pathToSave.toString())
            writer.print(rFile.text)
            writer.close()
        }
        val tokens = rFile.node.getAllChildrenNodes().filter { it.psi !is PsiWhiteSpace }.size
        tokensSum += tokens
        log.debug("TOKENS = $tokens ")
        return rFile
    }

    var tokensSum: Long = 0

    fun doTransformations(checker: CompilerTestChecker, isProject: Boolean = false, projectDir: String = "") {
        println("Size = ${psiFiles.size}")
        for (file in psiFiles) {
            doTransformationsForFile(file as KtFile, checker, isProject, projectDir)
//            //Saving
//            val pathToSave = StringBuilder(file.name)
//            pathToSave.insert(pathToSave.indexOfLast { it == '/' }, "/minimized")
//            File(pathToSave.substring(0, pathToSave.indexOfLast { it == '/' })).mkdirs()
//            val writer = PrintWriter(pathToSave.toString())
//            writer.print(reducedFile.text)
//            writer.close()
        }
    }


    fun doForParallelSimpleTransformations(
        isProject: Boolean = false,
        projectDir: String = "",
        checker: CompilerTestChecker
    ): KtFile? {
        //Temporary
        for ((i, file) in psiFiles.withIndex()) {
            log.debug("FILE NAME = ${file.second}")
            log.debug("FILE NUM = $i")
            //file.beforeAstChange()
            val pathToSave = StringBuilder(file.second)
            pathToSave.insert(pathToSave.indexOfLast { it == '/' }, "/minimized")
            var rFile = file.copy() as KtFile
            checker.pathToFile = rFile.name
            log.debug("proj = ${projectDir}")
            if (isProject) {
                println("PROJ = $projectDir isProj = $isProject File = ${file.second}")
                checker.init(projectDir, ktFactory!!)
            } else
                checker.init(file.second, ktFactory!!)
            checker.refreshAlreadyCheckedConfigurations()
            log.debug("ERROR = ${checker.getErrorInfo()}")
//            if (CommonCompilerCrashTestChecker.getErrorInfo().type == ErrorType.UNKNOWN)
//                continue
            checker.pathToFile = rFile.name
            SimplifyFunAndProp(rFile, checker).transform()
            val newText = PeepholePasses(rFile.text, checker, true).transform()
            rFile = KtPsiFactory(rFile.project).createFile(rFile.name, newText)
            ConstructionsDeleter(rFile, checker).transform()
            //X3
//            RemoveParameterFromDeclaration(rFile, CCTC, files).transform()
            //RemoveWhitespaces(rFile, CCTC).transform()
            RemoveUnusedImports(rFile, checker).transform()
            log.debug("VERIFY = ${checker.checkTest(rFile.text, rFile.name)}")
            return rFile
        }
        return null
    }
}
