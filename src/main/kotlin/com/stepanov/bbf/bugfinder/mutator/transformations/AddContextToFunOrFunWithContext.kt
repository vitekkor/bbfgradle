package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.generators.RandomFunctionGenerator
import com.stepanov.bbf.bugfinder.mutator.transformations.abi.gstructures.GClass
import com.stepanov.bbf.bugfinder.util.*
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.parents
import org.jetbrains.kotlin.resolve.BindingContext
import kotlin.random.Random
import kotlin.system.exitProcess

//TODO gen function bodies
class AddContextToFunOrFunWithContext : Transformation() {
    override fun transform() {
        repeat(5) {
            if (Random.getTrue(50)) {
                addContext()
            } else {
                addFuncWithCtx()
            }
        }
    }

    private fun addContext() {
        val ktFile = file as KtFile
        val ctx = PSICreator.analyze(ktFile, project) ?: return
        val randomComponentToPutContext =
            file.getAllPSIChildrenOfTwoTypes<KtProperty, KtNamedFunction>().randomOrNull() ?: return
        val randomContext = getRandomClassToMakeContext(ktFile, ctx) ?: return
        val randomComponentWithCtx =
            try {
                "context($randomContext)\n${randomComponentToPutContext.text}".let {
                    val factory = Factory.psiFactory
                    if (randomComponentToPutContext is KtNamedFunction) {
                        factory.createFunction(it)
                    } else {
                        factory.createProperty(it)
                    }
                }
            } catch (e: Exception) {
                return
            }
        randomComponentToPutContext.replaceThis(randomComponentWithCtx)
        if (!checker.checkCompiling()) {
            randomComponentWithCtx.replaceThis(randomComponentToPutContext)
        }
    }

    private fun addFuncWithCtx() {
        val ktFile = file as KtFile
        val fileBackup = ktFile.copy() as KtFile
        val ctx = PSICreator.analyze(ktFile, project) ?: return
        val randomLineToInsert = Random.nextInt(1, ktFile.text.count { it == '\n' } - 2)
        val nbw = ktFile.getNodesBetweenLines(randomLineToInsert, randomLineToInsert + 1).lastOrNull()
        val nodeToInsert =
            ktFile
                .getAllPSIDFSChildrenOfType<PsiElement>()
                .sublistAfter(nbw).first { it is PsiWhiteSpace && it.textContains('\n') } ?: return
        val instance = getRandomClassToMakeContext(ktFile, ctx) ?: return
        val parentClass =
            nodeToInsert.parents.find { it is KtClassOrObject }?.let { GClass.fromPsi(it as KtClassOrObject) }
        val randomGeneratedFunc = RandomFunctionGenerator(ktFile, ctx, parentClass).generate() ?: return
        val randomGeneratedFuncWithCtx =
            try {
                Factory.psiFactory.createBlock("\ncontext(${instance.substringBefore('(')})\n${randomGeneratedFunc.text}\n")
                    .also { it.rBrace!!.delete(); it.lBrace!!.delete() }
            } catch (e: Exception) {
                return
            }
        nodeToInsert.replace(randomGeneratedFuncWithCtx)
        if (!checker.checkCompiling()) {
            checker.curFile.changePsiFile(fileBackup, false)
        }
    }

    private fun getRandomClassToMakeContext(ktFile: KtFile, ctx: BindingContext): String? {
        val randomClassToMakeCtx = ktFile.getAllPSIChildrenOfType<KtClass>().randomOrNull() ?: return null
        val classDescriptor =
            randomClassToMakeCtx.getDeclarationDescriptorIncludingConstructors(ctx) as? ClassDescriptor ?: return null
        val res =
            if (Random.getTrue(25))
                RandomInstancesGenerator(ktFile, ctx).tryToGenerateValueOfType(classDescriptor.defaultType, 1).substringBefore('(')
            else
                classDescriptor.name.asString()
        return res.ifEmpty { null }
    }
}