//package com.stepanov.bbf.reduktor.passes
//
//import com.stepanov.bbf.reduktor.executor.CompilerTestChecker
//import com.stepanov.bbf.reduktor.util.getAllChildrenNodes
//import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
//import com.stepanov.bbf.reduktor.util.replaceThis
//
//import org.apache.log4j.Logger
//import org.jetbrains.kotlin.KtNodeTypes
//import org.jetbrains.kotlin.lexer.KtTokens
//import org.jetbrains.kotlin.psi.KtCallExpression
//import org.jetbrains.kotlin.psi.KtFile
//import org.jetbrains.kotlin.psi.KtNamedFunction
//import org.jetbrains.kotlin.psi.KtParameter
//import org.jetbrains.kotlin.psi.psiUtil.getCallNameExpression
//
//class RemoveParameterFromDeclaration(,
//                                     private val projectFiles: List<KtFile> = listOf(file)) {
//    override fun simplify() {
//        val functions = file.node.getAllChildrenNodes()
//                .filter { it.elementType == KtNodeTypes.FUN }
//                .map { it.psi as KtNamedFunction }
//        while (true)
//            if (!functionProcessing(file, functions))
//                break
//    }
//
//    private fun functionProcessing(file: KtFile, functions: List<KtNamedFunction>): Boolean {
//        var oneMoreProcessing = false
//        for (f in functions) {
//            val body = f.bodyExpression ?: continue
//            var index = 0
//            for (p in f.valueParameters) {
//                val id = p.nameIdentifier ?: continue
//                val isUsed = body.node.getAllChildrenNodes()
//                        .filter { it.elementType == KtTokens.IDENTIFIER }
//                        .any { it.text == id.text }
//                if (!isUsed) {
//                    val pCopy = p.copy() as KtParameter
//                    val nextArg = f.valueParameters.getOrNull(index + 1)
//                    val invocationsCopies = mutableListOf<KtCallExpression>()
//                    val invocations = getAllInvocations(f)
//                    for (i in invocations) {
//                        invocationsCopies.add(i.copy() as KtCallExpression)
//                        if (index >= i.valueArgumentList?.arguments?.size ?: continue) {
//                            val newIndex = i.valueArgumentList!!.arguments.size - index
//                            i.lambdaArguments.removeAt(newIndex)
//                        } else {
//                            i.valueArgumentList?.removeArgument(index)
//                        }
//                    }
//                    f.valueParameterList?.removeParameter(index)
//                    val res = checker.checkTest(file.text, file.name)
//                    if (!res) {
//                        if (nextArg != null) {
//                            f.valueParameterList?.addParameterBefore(pCopy, nextArg)
//                        } else {
//                            f.valueParameterList?.addParameter(pCopy)
//                        }
//                        for (i in 0 until invocations.size) {
//                            invocations[i].replaceThis(invocationsCopies[i])
//                        }
//                    } else {
//                        log.debug("SUCCESSFUL REMOVING parameter ${p.text} from ${f.valueParameterList?.text}")
//                        oneMoreProcessing = true
//                        --index
//                    }
//                }
//                ++index
//            }
//        }
//        return oneMoreProcessing
//    }
//
//    private fun getAllInvocations(func: KtNamedFunction): List<KtCallExpression> {
//        val res = mutableListOf<KtCallExpression>()
//        projectFiles.forEach { f ->
//            f.getAllPSIChildrenOfType<KtCallExpression>()
//                    .filter {
//                          it.getCallNameExpression()?.getReferencedName() == func.name &&
//                                it.valueArguments.size == func.valueParameters.size
//                    }
//                    .forEach { res.add(it) }
//        }
//        return res
//    }
//
//    private val log = Logger.getLogger("transformationManagerLog")
//}