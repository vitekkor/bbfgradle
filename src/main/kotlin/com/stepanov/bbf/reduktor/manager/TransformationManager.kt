package com.stepanov.bbf.reduktor.manager

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.project.LANGUAGE
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
import com.stepanov.bbf.reduktor.passes.*
import com.stepanov.bbf.reduktor.passes.slicer.Slicer
import com.stepanov.bbf.reduktor.util.ReduKtorProperties
import org.apache.log4j.Logger
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import kotlin.system.exitProcess

class TransformationManager(val checker: CompilerTestChecker) {

    var ktFactory: KtPsiFactory? = null
    private val log = Logger.getLogger("transformationManagerLog")

    init {
        SimplificationPass.checker = checker
        ktFactory = Factory.psiFactory
    }

    fun doProjectTransformations() {
        for (file in checker.project.files) {
            if (file.getLanguage() == LANGUAGE.KOTLIN) {
                checker.curFile = file
                doTransformationsForFile()
            }
        }
    }

    //    {
//        val path = targetFiles.joinToString(" ") { it.second }
//        val errorInfo = checker.init(path, KtPsiFactory(targetFiles[0].first))
//        val res = mutableListOf<PsiFile>()
//        for ((i, file) in targetFiles.map { it.first }.withIndex()) {
//            //checker.otherFiles = Project(targetFiles.map { it.first.text }.getAllWithout(i))
//            checker.otherFiles =
//                Project(res.map { it.text } + targetFiles.map { it.first.text }.let { it.subList(i + 1, it.size) })
//            checker.filePos = i
//            if (file.text.getFileLanguageIfExist() == LANGUAGE.KOTLIN) {
//                log.debug("Reducing of ${file.name} began")
//                res.add(doTransformationsForFile(file as KtFile, checker))
//            } else {
//                var rFile = file.copy() as PsiFile
//                log.debug("Reducing of ${file.name} began")
//                val HDD = HierarchicalDeltaDebugger(rFile.node, checker)
//                HDD.hdd()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                RemoveWhitespaces(rFile, checker).transform()
//                res.add(rFile.copy() as PsiFile)
//                log.debug("Reduced ${file.name} = ${rFile.text}")
//            }
//        }
//        return res
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

    private var prevState = checker.curFile.text
    private fun executePass(pass: SimplificationPass) {
        pass.simplify()
        log.debug("Changes after ${pass::class.simpleName} = ${SimplificationPass.checker.curFile.text != prevState}")
        log.debug("Verify = ${checker.checkTest()}")
        prevState = checker.curFile.text
    }

    fun doTransformationsForFile() {
        val file = checker.curFile
        log.debug("FILE NAME = ${file.name}")
        log.debug("Content = ${file.text}")
        (file.psiFile as KtFile).beforeAstChange()
        require(checker.checkTest()) { "No bug" }
        checker.refreshAlreadyCheckedConfigurations()
        var oldRes = file.text
        while (true) {
            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
                executePass(RemoveSuperTypeList())
                executePass(SimplifyParameterList())
                executePass(SimplifyContainerType())
                executePass(SimplifyControlExpression())
                executePass(SimplifyFunAndProp())
                executePass(ReplaceBlockExpressionToBody())
                executePass(SimplifyWhen())
                executePass(ReturnValueToVoid())
                executePass(ElvisOperatorSimplifier())
                executePass(TryCatchDeleter())
                executePass(SimplifyIf())
                executePass(SimplifyFor())
//              executePass(SimplifyLambdaExpression)
//              executePass(RemoveInheritance)
//              executePass(SimplifyInheritance)
                executePass(SimplifyConstructor())
                executePass(ReturnValueToConstant())
                executePass(SimplifyBlockExpression())
                executePass(SimplifyBinaryExpression())
                executePass(SimplifyStringConstants())
            }
            if (ReduKtorProperties.getPropAsBoolean("SLICING") == true) {
                executePass(Slicer)
            }
            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
                executePass(MinorSimplifyings())
                executePass(RemoveParameterFromDeclaration())
                executePass(FunInliner())
                executePass(ReplaceArgOnTODO())
                executePass(SimplifyCallExpression())
                executePass(ValueArgumentListSimplifying())
                executePass(PeepholePasses())
                executePass(ConstructionsDeleter())
            }
            if (ReduKtorProperties.getPropAsBoolean("HDD") == true) {
                executePass(HierarchicalDeltaDebugger())
            }
            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
                executePass(EqualityMapper())
                executePass(FunSimplifier())
                executePass(TypeChanger())
            }
            executePass(RemoveWhitespaces())
            log.debug("CURRENT RESULT = ${checker.curFile.text}")
            if (checker.curFile.text.filterNot { it.isWhitespace() } == oldRes.filterNot { it.isWhitespace() }) {
                break
            }
            oldRes = checker.curFile.text

//            if (ReduKtorProperties.getPropAsBoolean("SLICING") == true) {
//                var errorInfo = checker.getErrorInfo()
//                Transformation.log.debug("ERROR INFO = $errorInfo")
//                Slicer(rFile, checker)
//                    .computeSlice(errorInfo.line, Slicer.Level.INTRAPROCEDURAL)
//                Transformation.log.debug("VERIFY INTREPR = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER INTREPR ${rFile.text != oldRes}")
//                errorInfo = checker.reinit()
//                Slicer(rFile, checker)
//                    .computeSlice(errorInfo.line, Slicer.Level.FUNCTION)
//                Transformation.log.debug("VERIFY FUNC = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER FUNC ${rFile.text != oldRes}")
//                errorInfo = checker.reinit()
//                Slicer(rFile, checker)
//                    .computeSlice(errorInfo.line, Slicer.Level.CLASS)
//                Transformation.log.debug("CHANGES AFTER CLASS ${rFile.text != oldRes}")
//                Transformation.log.debug("VERIFY CLASS = ${checker.checkTest(rFile.text)}")
//            }
//            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
//                executePass(MinorSimplifyings())
////                //Get context
//                File(rFile.name).writeText(rFile.text)
//                val creator = PSICreator("")
//                rFile = creator.getPSIForFile(rFile.name, true)
//                if (creator.ctx != null) {
//                    MinorSimplifyings(rFile, checker, creator.ctx!!).transform()
//                    rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                    Transformation.log.debug("VERIFY MinorSimplifyings = ${checker.checkTest(rFile.text)}")
//                    Transformation.log.debug("CHANGES AFTER MinorSimplifyings ${rFile.text != oldRes}")
//                }
//                RemoveParameterFromDeclaration(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("VERIFY RemoveParameterFromDeclaration = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER RemoveParameterFromDeclaration ${rFile.text != oldRes}")
//                FunInliner(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("VERIFY FunInliner = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER FunInliner ${rFile.text != oldRes}")
//                ReplaceArgOnTODO(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("VERIFY ReplaceArgOnTODO = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER ReplaceArgOnTODO ${rFile.text != oldRes}")
//                SimplifyCallExpression(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("VERIFY SimplifyCallExpression = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER SimplifyCallExpression ${rFile.text != oldRes}")
//                ValueArgumentListSimplifying(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("VERIFY ValueArgumentListSimplifying = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER ValueArgumentListSimplifying ${rFile.text != oldRes}")
//                val newText = PeepholePasses(rFile.text, checker, false).transform()
//                Transformation.log.debug("CHANGES AFTER PEEPHOLE ${newText != oldRes}")
//                Transformation.log.debug("VERIFY PEEPHOLE = ${checker.checkTest(newText)}")
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, newText)
//                Transformation.log.debug("UPDATED ${checker.checkTest(rFile.text)}")
//                ConstructionsDeleter(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("CHANGES AFTER EMPTY ${rFile.text != oldRes}")
//                Transformation.log.debug("VERIFY EMPTY = ${checker.checkTest(rFile.text)}")
//            }
//            if (ReduKtorProperties.getPropAsBoolean("FASTREDUCE") == true) {
//                PSIReducer(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("CHANGES AFTER FASTREDUCE ${rFile.text != oldRes}")
//                Transformation.log.debug("VERIFY FASTREDUCE = ${checker.checkTest(rFile.text)}")
//            }
//            if (ReduKtorProperties.getPropAsBoolean("HDD") == true) {
//                val HDD = HierarchicalDeltaDebugger(rFile.node, checker)
//                HDD.hdd()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("CHANGES AFTER HDD ${rFile.text != oldRes}")
//                Transformation.log.debug("VERIFY HDD = ${checker.checkTest(rFile.text)}")
//            }
//            if (ReduKtorProperties.getPropAsBoolean("TRANSFORMATIONS") == true) {
//                EqualityMapper(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("CHANGES AFTER EqualityMapper ${rFile.text != oldRes}")
//                Transformation.log.debug("VERIFY EqualityMapper = ${checker.checkTest(rFile.text)}")
//                FunSimplifier(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("VERIFY FunSimplifier = ${checker.checkTest(rFile.text)}")
//                Transformation.log.debug("CHANGES AFTER FunSimplifier ${rFile.text != oldRes}")
//                TypeChanger(rFile, checker).transform()
//                rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//                Transformation.log.debug("CHANGES AFTER TypeChanger ${rFile.text != oldRes}")
//                Transformation.log.debug("VERIFY TypeChanger = ${checker.checkTest(rFile.text)}")
//            }
//            RemoveWhitespaces(rFile, checker).transform()
//            rFile = KtPsiFactory(rFile.project).createFile(rFile.name, rFile.text)
//            Transformation.log.debug("CURRENT RESULT = ${rFile.text}")
//            if (rFile.text.filterNot { it.isWhitespace() } == oldRes.filterNot { it.isWhitespace() }) {
//                //if (rFile.text.filterNot { it == '\n' } == oldRes.filterNot { it == '\n' }) {
//                break
//            }
//            oldRes = rFile.text
//        }
//        Transformation.log.debug("VERIFY = ${checker.checkTest(rFile.text)}")
//        Transformation.log.debug("RESULT: ${rFile.text}")
////        SAVING
//        if (!isProject && ReduKtorProperties.getPropAsBoolean("SAVE_RESULT") == true) {
//            File(pathToSave.toString().substringBeforeLast('/')).mkdirs()
//            val writer = PrintWriter(pathToSave.toString())
//            writer.print(rFile.text)
//            writer.close()
//        }
//        val tokens = rFile.node.getAllChildrenNodes().filter { it.psi !is PsiWhiteSpace }.size
//        tokensSum += tokens
//        Transformation.log.debug("TOKENS = $tokens ")
//        return rFile
        }
    }

        var tokensSum: Long = 0

//    fun doForParallelSimpleTransformations(
//        isProject: Boolean = false,
//        projectDir: String = "",
//        checker: CompilerTestChecker
//    ): KtFile? {
//        //Temporary
//        for ((i, file) in psiFiles.withIndex()) {
//            log.debug("FILE NAME = ${file.second}")
//            log.debug("FILE NUM = $i")
//            //file.beforeAstChange()
//            val pathToSave = StringBuilder(file.second)
//            pathToSave.insert(pathToSave.indexOfLast { it == '/' }, "/minimized")
//            var rFile = file.copy() as KtFile
//            checker.pathToFile = rFile.name
//            log.debug("proj = ${projectDir}")
//            if (isProject) {
//                println("PROJ = $projectDir isProj = $isProject File = ${file.second}")
//                checker.init(projectDir, ktFactory!!)
//            } else
//                checker.init(file.second, ktFactory!!)
//            checker.refreshAlreadyCheckedConfigurations()
//            log.debug("ERROR = ${checker.getErrorInfo()}")
////            if (CommonCompilerCrashTestChecker.getErrorInfo().type == ErrorType.UNKNOWN)
////                continue
//            checker.pathToFile = rFile.name
//            SimplifyFunAndProp(rFile, checker).transform()
//            val newText = PeepholePasses(rFile.text, checker, true).transform()
//            rFile = KtPsiFactory(rFile.project).createFile(rFile.name, newText)
//            ConstructionsDeleter(rFile, checker).transform()
//            //X3
////            RemoveParameterFromDeclaration(rFile, CCTC, files).transform()
//            //RemoveWhitespaces(rFile, CCTC).transform()
//            RemoveUnusedImports(rFile, checker).transform()
//            log.debug("VERIFY = ${checker.checkTest(rFile.text, rFile.name)}")
//            return rFile
//        }
//        return null
//    }
}


