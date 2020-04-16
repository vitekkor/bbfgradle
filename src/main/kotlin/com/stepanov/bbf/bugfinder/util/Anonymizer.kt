package com.stepanov.bbf.bugfinder.util

import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.MutationChecker
import com.stepanov.bbf.reduktor.parser.PSICreator
import com.stepanov.bbf.reduktor.util.getAllChildren
import org.jetbrains.kotlin.psi.*
import java.io.File

class Anonymizer {

    fun anon(dir: String) {
        for ((i, f) in File(dir).listFiles().sortedBy { it.length() }.withIndex()) {
            println("i = $i")
            val checker = MutationChecker(listOf(JVMCompiler()))
            if (!checker.checkTextCompiling(f.readText())) {
                if (f.readText().contains("// FILE")) {
                    continue
                }
                continue
            }
            /*if (f.name != "singleAssignmentToVarargInAnnotation.kt") continue*/
            val creator = PSICreator("")
            val psi = creator.getPSIForFile(f.absolutePath)
            val named = mutableListOf<KtNamedDeclaration>()
            for (child in psi.getAllChildren()) {
                if (child is KtNamedDeclaration && child.name != null && !child.name!!.startsWith("<") &&
                    !child.name!!.contains("box")) {
                    if (named.all { it.name != child.name })
                        named.add(child)
                }
            }
            val counters = mutableListOf(0, 0, 0, 0, 0)
            println(named.map { it to it.name })
            for (n in named) {
                val newName = when (n) {
                    is KtClassOrObject -> "Kl${counters[0]++}"
                    is KtFunction -> "f${counters[1]++}"
                    is KtProperty -> "prop${counters[2]++}"
                    is KtParameter -> "par${counters[3]++}"
                    else -> "smth${counters[4]++}"
                }
                val oldExpr = n.copy()
                print("changing ${n.name} on $newName")
                val usages = psi.getAllPSIChildrenOfType<KtNameReferenceExpression>().filter { it.text == n.name }
                val newNameRef = KtPsiFactory(psi.project).createSimpleName(newName)
                val newUsages = usages.map {
                    val copy = newNameRef.copy()
                    it.replaceThis(copy)
                    it.copy() to copy
                }
                n.setName(newName)
                val res = checker.checkCompiling(psi)
                println(" = $res")
                if (!res) {
                    newUsages.forEach { it.second.replaceThis(it.first) }
                    n.replaceThis(oldExpr)
                    if (!checker.checkCompiling(psi)) {
                        println(psi.text)
                        System.exit(0)
                    }
                }
            }
            f.writeText(psi.text)
        }
    }
}