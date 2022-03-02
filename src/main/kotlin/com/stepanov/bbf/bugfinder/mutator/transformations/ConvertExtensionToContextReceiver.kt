package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.mutator.transformations.Factory.tryToCreateExpression
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import com.stepanov.bbf.reduktor.util.replaceThis
import org.jetbrains.kotlin.cfg.getDeclarationDescriptorIncludingConstructors
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.resolve.calls.util.getResolvedCall
import java.lang.Exception

class ConvertExtensionToContextReceiver : Transformation() {
    override fun transform() {
        val ktFile = file as KtFile
        val randomExtensionFunction =
            ktFile.getAllPSIChildrenOfType<KtNamedFunction>()
                .filter { it.isExtensionDeclaration() }
                .randomOrNull() ?: return
        val fileBackup = ktFile.copy() as KtFile
        val ctx = PSICreator.analyze(ktFile, project) ?: return
        val funDescriptor = randomExtensionFunction.getDeclarationDescriptorIncludingConstructors(ctx) ?: return
        val funCalls = ktFile.getAllPSIChildrenOfType<KtCallExpression>()
            .filter { it.getResolvedCall(ctx)?.candidateDescriptor == funDescriptor }
        val extensionAsContext =
            with(randomExtensionFunction.copy() as KtNamedFunction) {
                val extensionName = receiverTypeReference?.text ?: return
                receiverTypeReference?.nextSibling?.delete()
                receiverTypeReference?.delete()
                getAllPSIChildrenOfType<KtThisExpression>().forEach {
                    it.replaceThis(
                        Factory.psiFactory.createExpression(
                            "this@$extensionName"
                        )
                    )
                }
                try {
                    Factory.psiFactory.createFunction(
                        """
                    context($extensionName)
                    $text
                """.trimIndent()
                    )
                } catch (e: Exception) {
                    return
                } catch (e: Error) {
                    return
                }
            }
        randomExtensionFunction.replaceThis(extensionAsContext)
        for (funCall in funCalls) {
            with(funCall) {
                if (parent !is KtDotQualifiedExpression) return@with
                val parentCall = (parent as KtDotQualifiedExpression).receiverExpression.text
                val newCall =
                    Factory.psiFactory.tryToCreateExpression("with($parentCall) { $text }") ?: return@with
                replaceThis(newCall)
            }
        }
        if (!checker.checkCompiling()) {
            checker.curFile.changePsiFile(fileBackup, false)
        }
    }
}