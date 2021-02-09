package com.stepanov.bbf.bugfinder.gitinfocollector

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiPackageStatement
import com.stepanov.bbf.coverage.CoverageEntry
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.psiUtil.parents

internal class JavaSignatureCollector : SignatureCollectorInterface<PsiMethod> {

    override fun collect(funcs: List<PsiMethod>): List<CoverageEntry> {
        if (funcs.isEmpty()) return listOf()
        val psiFile = funcs.first().parents.last() as PsiFile
        val packageDirective =
            psiFile.getAllChildren()
                .firstOrNull { it is PsiPackageStatement }
                ?.text
                ?.substringAfter("package ")
                ?.replace('.', '/')
                ?.dropLast(1) ?: ""
        return funcs.map { f ->
            val path =
                f.parents.filterIsInstance<PsiClass>()
                    .filter { it.name != null && it.name!!.trim().isNotEmpty() }
                    .joinToString(":") { it.name!! }
            val name = f.name ?: ""
//            val args = f.parameters.let {
//                if (it.isEmpty()) "()"
//                else it.joinToString(separator = ";", prefix = "(", postfix = ";)") {
//                    it.type.toString().substringAfter("PsiType:").substringBefore('<')
//                }
//            }
            val params = f.parameters.map { it.type.toString().substringAfter("PsiType:").substringBefore('<') }
            val rtv =
                f.returnTypeElement?.type?.toString()?.substringAfter("PsiType:")?.substringBefore('<') ?: ""
            //"$packageDirective/$path:$name$args$rtv;"
            CoverageEntry("$packageDirective/$path", name, params, rtv)
        }
    }

}