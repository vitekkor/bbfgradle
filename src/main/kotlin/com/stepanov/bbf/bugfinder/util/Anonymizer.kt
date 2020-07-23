package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.executor.Checker
import com.stepanov.bbf.bugfinder.executor.checkers.CompilationChecker
import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.*
import kotlin.random.Random

object Anonymizer {

    val counters = mutableListOf(0, 0, 0, 0, 0)
    fun anon(psi: KtFile): Boolean {
        val checker = Checker(JVMCompiler())

        /*if (f.name != "singleAssignmentToVarargInAnnotation.kt") continue*/
        val named = mutableListOf<KtNamedDeclaration>()
        for (child in psi.getAllChildren()) {
            if (child is KtNamedDeclaration && child.name != null && !child.name!!.startsWith("<") &&
                !child.name!!.contains("box")
            ) {
                if (named.all { it.name != child.name })
                    named.add(child)
            }
        }
        //Rename box
        psi.getAllPSIChildrenOfType<KtNamedFunction>().firstOrNull { it.name?.startsWith("box") == true }
            ?.setName("box${Random.nextInt(100, 1000)}")
        for (n in named) {
            val newName = when (n) {
                is KtClassOrObject -> "Kl${counters[0]++}"
                is KtFunction -> "f${counters[1]++}"
                is KtProperty -> "prop${counters[2]++}"
                is KtParameter -> "par${counters[3]++}"
                else -> "smth${counters[4]++}"
            }
            val oldExpr = n.copy()
            val usages = psi.getAllPSIChildrenOfType<KtNameReferenceExpression>().filter { it.text == n.name }
            val newNameRef = KtPsiFactory(psi.project).createSimpleName(newName)
            val newUsages = usages.map {
                val copy = newNameRef.copy()
                it.replaceThis(copy)
                it.copy() to copy
            }
            n.setName(newName)
            val res = checker.checkCompiling(Project.createFromCode(psi.text))
            if (!res) {
                newUsages.forEach { it.second.replaceThis(it.first) }
                n.replaceThis(oldExpr)
                if (!checker.checkCompiling(Project.createFromCode(psi.text))) {
                    return false
                }
            }
        }
        return true
    }
//
//    //Shit code here =(
//    fun anonymizeAnonimized(psi: PsiFile, counters: MutableList<Int>, fl: Boolean) {
//        val named = mutableListOf<KtNamedDeclaration>()
//        for (child in psi.getAllChildren()) {
//            if (child is KtNamedDeclaration && child.name != null && !child.name!!.startsWith("<") &&
//                child.name!!.contains(Regex("""(Kl|f|prop|par|smth)\d+"""))
//            ) {
//                if (named.all { it.name != child.name })
//                    named.add(child)
//            }
//        }
//
//        for (n in named) {
//            val newName =
//                if (fl)
//                    when (n) {
//                        is KtClassOrObject -> "myKl${counters[0]++}"
//                        is KtFunction -> "myfu${counters[1]++}"
//                        is KtProperty -> "myprop${counters[2]++}"
//                        is KtParameter -> "mypar${counters[3]++}"
//                        else -> "mysmth${counters[4]++}"
//                    }
//                else when (n) {
//                    is KtClassOrObject -> "Kl${counters[0]++}"
//                    is KtFunction -> "fu${counters[1]++}"
//                    is KtProperty -> "prop${counters[2]++}"
//                    is KtParameter -> "par${counters[3]++}"
//                    else -> "smth${counters[4]++}"
//                }
//            val usages = psi.getAllPSIChildrenOfType<KtNameReferenceExpression>().filter { it.text == n.name }
//            val newNameRef = KtPsiFactory(psi.project).createSimpleName(newName)
//            usages.map {
//                val copy = newNameRef.copy()
//                it.replaceThis(copy)
//                it.copy() to copy
//            }
//            n.setName(newName)
//        }
//    }
}