package com.stepanov.bbf.reduktor.parser

import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.extensions.ExtensionPoint
import com.intellij.openapi.extensions.Extensions
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.impl.source.tree.TreeCopyHandler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.mutator.transformations.Factory
import com.stepanov.bbf.kootstrap.FooBarCompiler.setupMyCfg
import com.stepanov.bbf.kootstrap.FooBarCompiler.setupMyEnv
import com.stepanov.bbf.kootstrap.util.opt
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.cli.common.config.addKotlinSourceRoots
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.cli.jvm.config.addJavaSourceRoots
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoots
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.JVMConfigurationKeys
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.lazy.JvmResolveUtil
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@Suppress("DEPRECATION")
object PSICreator {

    private var targetFiles: List<KtFile> = listOf()
    private lateinit var cfg: CompilerConfiguration
    private lateinit var env: KotlinCoreEnvironment
    var curProject: com.stepanov.bbf.bugfinder.executor.project.Project? = null

    fun getPsiForJava(text: String, proj: Project = Factory.file.project) =
        PsiFileFactory.getInstance(proj).createFileFromText(JavaLanguage.INSTANCE, text)

    fun getPsiForJavaWithName(text: String, name: String, proj: Project = Factory.file.project) =
        PsiFileFactory.getInstance(proj).createFileFromText(name, JavaLanguage.INSTANCE, text)

    fun getPSIForText(text: String): KtFile {
        //Save to tmp
        val path = "tmp/tmp.kt"
        File(path).writeText(text)
        return getPSIForFile(path)
    }

    fun getPsiForTextWithName(text: String, fileName: String): KtFile {
        val path = "tmp/$fileName"
        File(path).writeText(text)
        return getPSIForFile(path)
    }

    fun getPSIForFile(path: String): KtFile {
        val newArgs = arrayOf("-t", path)

        val cmd = opt.parse(newArgs)

        cfg = setupMyCfg(cmd)
        env = setupMyEnv(cfg)

        if (!Extensions.getRootArea().hasExtensionPoint(TreeCopyHandler.EP_NAME.name)) {
            Extensions.getRootArea().registerExtensionPoint(
                TreeCopyHandler.EP_NAME.name,
                TreeCopyHandler::class.java.canonicalName,
                ExtensionPoint.Kind.INTERFACE
            )
        }

        targetFiles = env.getSourceFiles().map {
            val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
            f.originalFile = it
            f
        }

        return targetFiles.first()
    }

    fun analyze(psiFile: PsiFile): BindingContext? = analyze(psiFile, curProject)

    fun analyzeAndGetModuleDescriptor(psiFile: PsiFile) = getAnalysisResult(psiFile, curProject)?.moduleDescriptor

    fun analyze(psiFile: PsiFile, project: com.stepanov.bbf.bugfinder.executor.project.Project?): BindingContext? =
        getAnalysisResult(psiFile, project)?.bindingContext

    private fun getAnalysisResult(
        psiFile: PsiFile,
        project: com.stepanov.bbf.bugfinder.executor.project.Project?
    ): AnalysisResult? {
        //if (psiFile !is KtFile) return null
        project?.saveOrRemoveToTmp(true)
        val cmd = opt.parse(arrayOf())
        val cfg = setupMyCfg(cmd)

        cfg.put(JVMConfigurationKeys.INCLUDE_RUNTIME, true)
        cfg.put(JVMConfigurationKeys.JDK_HOME, File(System.getProperty("java.home")))
        cfg.addJvmClasspathRoots(
            listOf(
                CompilerArgs.getStdLibPath("kotlin-test"),
                CompilerArgs.getStdLibPath("kotlin-test-common"),
                CompilerArgs.getStdLibPath("kotlin-test-annotations-common"),
                //CompilerArgs.getStdLibPath("kotlin-test-junit"),
                CompilerArgs.getStdLibPath("kotlin-reflect"),
                CompilerArgs.getStdLibPath("kotlin-stdlib-common"),
                CompilerArgs.getStdLibPath("kotlin-stdlib"),
                CompilerArgs.getStdLibPath("kotlin-stdlib-jdk8")
            ).map { File(it) }
        )

        project?.files?.map { it.name }?.let { fileNames ->
            val kotlinSources = fileNames.filter { it.endsWith(".kt") }
            val javaSources = fileNames.filter { it.endsWith(".java") }
            cfg.addJavaSourceRoots(javaSources.map { File(it) })
            cfg.addKotlinSourceRoots(kotlinSources)
        }

        val env = setupMyEnv(cfg)
        val configuration = env.configuration.copy()
        configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
        val executor = Executors.newCachedThreadPool()
        val task = Callable {
            try {
                if (psiFile is KtFile) {
                    JvmResolveUtil.analyze(listOf(psiFile), env, configuration)
                } else {
                    JvmResolveUtil.analyze(env)
                }
            } catch (e: Exception) {
                println(e)
                null
            } catch (e: Error) {
                null
            }
        }
        val taskResult = executor.submit(task)
        return try {
            taskResult.get(5, TimeUnit.SECONDS)
        } catch (e: Exception) {
            null
        }
//        return try {
//            if (psiFile is KtFile) {
//                JvmResolveUtil.analyze(listOf(psiFile), env, configuration)
//            } else {
//                JvmResolveUtil.analyze(env)
//            }
//        } catch (e: Exception) {
//            println(e)
//            null
//        } catch (e: Error) {
//            null
//        } finally {
//            //project?.saveOrRemoveToTmp(false)
//        }
    }


//    fun analyze(psiFile: PsiFile): BindingContext? {
//        try {
//            val cmd = opt.parse(arrayOf())
//
//            val cfg = setupMyCfg(cmd)
//            val env = setupMyEnv(cfg)
//            val configuration = env.configuration.copy()
//
//            configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
//            configuration.put(
//                JSConfigurationKeys.LIBRARIES, listOf(
//                    CompilerArgs.getStdLibPath("kotlin-stdlib-js"),
//                    //CompilerArgs.getStdLibPath("kotlin-test-js"),
//                    CompilerArgs.getStdLibPath("kotlin-test"),
//                    CompilerArgs.getStdLibPath("kotlin-test-common"),
//                    CompilerArgs.getStdLibPath("kotlin-test-annotations-common"),
//                    CompilerArgs.getStdLibPath("kotlin-test-junit"),
//                    CompilerArgs.getStdLibPath("kotlin-reflect")
//                )
//            )
////                val sourcesOnly = TopDownAnalyzerFacadeForJVM.newModuleSearchScope(psiFile.project, listOf(psiFile as KtFile))
////                return TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(
////                    psiFile.project,
////                    listOf(psiFile as KtFile),
////                    NoScopeRecordCliBindingTrace(),
////                    env.configuration,
////                    env::createPackagePartProvider
////                    //{ scope -> JvmPackagePartProvider(LANGUAGE_FEATURE_SETTINGS, scope) }
////                ).bindingContext
//            return TopDownAnalyzerFacadeForJS.analyzeFiles(
//                (listOf(psiFile as KtFile)),
//                JsConfig(env.project, configuration, CompilerEnvironment)
//            ).bindingContext
//        } catch (e: Exception) {
//            println(e)
//            return null
//        }
//    }


//    fun getJavaFiles(projectDir: String): List<PsiFile> {
//        findAndCreateJavaFiles(projectDir)
//        return javaFiles
//    }
//
//    private fun findAndCreateJavaFiles(projectDir: String) {
//        val folder = File(projectDir)
//        for (entry in folder.listFiles()) {
//            if (entry.isDirectory) {
//                findAndCreateJavaFiles(entry.absolutePath)
//            } else if (entry.name.endsWith(".java")) {
//                val javaFile =
//                    PsiFileFactory.getInstance(env.project).createFileFromText(JavaLanguage.INSTANCE, entry.readText())
//                javaFiles.add(javaFile)
//            }
//        }
//    }


//    fun reinit(projectDir: String): List<KtFile> {
//        this.projectDir = projectDir
//        val new_args = arrayOf("-t", projectDir)
//        val cmd = opt.parse(new_args)
//
//        env = setupMyEnv(cfg)
//        if (!Extensions.getRootArea().hasExtensionPoint(TreeCopyHandler.EP_NAME.name)) {
//            Extensions.getRootArea().registerExtensionPoint(
//                TreeCopyHandler.EP_NAME.name,
//                TreeCopyHandler::class.java.canonicalName,
//                ExtensionPoint.Kind.INTERFACE
//            )
//        }
//
//        val ktFiles = env.getSourceFiles().map {
//            val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
//            f.originalFile = it
//            f
//        }
//
//        targetFiles = ktFiles.filter { f ->
//            cmd.targetRoots.any { root ->
//                f.originalFile.virtualFile.path.startsWith(root)
//            }
//        }
//
//        return targetFiles
//    }
//
//
//    fun getPsiForTextWithName(text: String, fileName: String, generateCtx: Boolean = true): KtFile {
//        val path = "tmp/$fileName"
//        File(path).writeText(text)
//        return getPSIForFile(path, generateCtx)
//    }
//
//    fun getPSIForFile(path: String, generateCtx: Boolean = true): KtFile {
//        val newArgs = arrayOf("-t", path)
//
//        val cmd = opt.parse(newArgs)
//
//        cfg = setupMyCfg(cmd)
//        env = setupMyEnv(cfg)
//
//        if (!Extensions.getRootArea().hasExtensionPoint(TreeCopyHandler.EP_NAME.name)) {
//            Extensions.getRootArea().registerExtensionPoint(
//                TreeCopyHandler.EP_NAME.name,
//                TreeCopyHandler::class.java.canonicalName,
//                ExtensionPoint.Kind.INTERFACE
//            )
//        }
//
//        targetFiles = env.getSourceFiles().map {
//            val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
//            f.originalFile = it
//            f
//        }
//
//        val file = targetFiles.first()
//
//        if (generateCtx) {
//            ctx = analyze(file)
//        }
//        return targetFiles.first()
//    }
//
//    companion object {
//
//        fun analyze(project: com.stepanov.bbf.bugfinder.executor.project.Project): BindingContext? {
//            project.saveOrRemoveToTmp(true)
//            //val cmd = opt.parse(project.files.map { it.name }.toTypedArray())
//            val cmd = opt.parse(arrayOf())
//            //val cmd = opt.parse(arrayOf("-t", "tmp/Sam.java:tmp/genericSam2.kt"))
//            println(cmd)
//
//            val cfg = setupMyCfg(cmd)
//            cfg.addKotlinSourceRoot("tmp/genericSam2.kt")
//            cfg.addJavaSourceRoot(File("tmp/Sam.java"))
//            //cfg.put()
//            val env = setupMyEnv(cfg)
//            val configuration = env.configuration.copy()
//            configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
//            //val files = project.files.map { it.psiFile }
//            return try {
//                val ctx = JvmResolveUtil.analyze(env.getSourceFiles(), env, cfg).bindingContext
//                val psi = env.getSourceFiles().first()
//                psi.getAllPSIChildrenOfType<KtExpression>().map { it.text to it.getType(ctx) }.filter { it.second != null }.forEach(::println)
//                ctx
//                //JvmResolveUtil.analyze(env).bindingContext
////                TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(
////                    env.project,
////                    files,
////                    CliBindingTrace(),
////                    cfg,
////                    { scope -> JvmPackagePartProvider(env.configuration.languageVersionSettings, scope) }
////                ).bindingContext
//            } catch (e: Exception) {
//                println(e)
//                null
//            }
//        }
//
//        fun analyze(psiFile: PsiFile): BindingContext? {
//            try {
//                val cmd = opt.parse(arrayOf())
//
//                val cfg = setupMyCfg(cmd)
//                val env = setupMyEnv(cfg)
//                val configuration = env.configuration.copy()
//
//                configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
//                configuration.put(
//                    JSConfigurationKeys.LIBRARIES, listOf(
//                        CompilerArgs.getStdLibPath("kotlin-stdlib-js"),
//                        //CompilerArgs.getStdLibPath("kotlin-test-js"),
//                        CompilerArgs.getStdLibPath("kotlin-test"),
//                        CompilerArgs.getStdLibPath("kotlin-test-common"),
//                        CompilerArgs.getStdLibPath("kotlin-test-annotations-common"),
//                        CompilerArgs.getStdLibPath("kotlin-test-junit"),
//                        CompilerArgs.getStdLibPath("kotlin-reflect")
//                    )
//                )
////                val sourcesOnly = TopDownAnalyzerFacadeForJVM.newModuleSearchScope(psiFile.project, listOf(psiFile as KtFile))
////                return TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(
////                    psiFile.project,
////                    listOf(psiFile as KtFile),
////                    NoScopeRecordCliBindingTrace(),
////                    env.configuration,
////                    env::createPackagePartProvider
////                    //{ scope -> JvmPackagePartProvider(LANGUAGE_FEATURE_SETTINGS, scope) }
////                ).bindingContext
//                return TopDownAnalyzerFacadeForJS.analyzeFiles(
//                    (listOf(psiFile as KtFile)),
//                    JsConfig(env.project, configuration, CompilerEnvironment)
//                ).bindingContext
//            } catch (e: Exception) {
//                println(e)
//                return null
//            }
//        }
//    }


    //    fun analyze(project: com.stepanov.bbf.bugfinder.executor.project.Project): BindingContext? {
//        project.saveOrRemoveToTmp(true)
//        val cmd = opt.parse(arrayOf())
//        val cfg = setupMyCfg(cmd)
//        project.files.map { it.name }.let { fileNames ->
//            val kotlinSources = fileNames.filter { it.endsWith(".kt") }
//            val javaSources = fileNames.filter { it.endsWith(".java") }
//            cfg.addJavaSourceRoots(javaSources.map { File(it) })
//            cfg.addKotlinSourceRoots(kotlinSources)
//        }
//
//        val env = setupMyEnv(cfg)
//        val configuration = env.configuration.copy()
//        configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
//        return try {
//            JvmResolveUtil.analyze()
//            //JvmResolveUtil.analyze(env.getSourceFiles(), env, cfg).bindingContext
//        } catch (e: Exception) {
//            println(e)
//            null
//        } finally {
//            project.saveOrRemoveToTmp(false)
//        }
////        cfg.addKotlinSourceRoot("tmp/genericSam2.kt")
////        cfg.addJavaSourceRoot(File("tmp/Sam.java"))
//        //cfg.put()

//        val configuration = env.configuration.copy()
//        configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
//        //val files = project.files.map { it.psiFile }
//        return try {
//            JvmResolveUtil.analyze(env.getSourceFiles(), env, cfg).bindingContext
//        } catch (e: Exception) {
//            println(e)
//            null
//        }
//        return null
//    }


}