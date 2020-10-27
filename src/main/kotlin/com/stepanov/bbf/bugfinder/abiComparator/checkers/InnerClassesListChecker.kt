package com.stepanov.bbf.bugfinder.abiComparator.checkers

import com.stepanov.bbf.bugfinder.abiComparator.classFlags
import com.stepanov.bbf.bugfinder.abiComparator.isSynthetic
import com.stepanov.bbf.bugfinder.abiComparator.listOfNotNull
import com.stepanov.bbf.bugfinder.abiComparator.reports.ClassReport
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.InnerClassNode

class InnerClassesListChecker : ClassChecker {
    override val name = "class.innerClasses"

    override fun check(class1: ClassNode, class2: ClassNode, report: ClassReport) {
        val innerClasses1 = class1.innerClasses.listOfNotNull<InnerClassNode>()
                .filter { it.innerName != null && it.innerName != "WhenMappings" }
                .associateBy { it.name }
        val innerClasses2 = class2.innerClasses.listOfNotNull<InnerClassNode>()
                .filter { it.innerName != null && it.innerName != "WhenMappings" }
                .associateBy { it.name }

        val relevantInnerClassNames =
                innerClasses1.keys.union(innerClasses2.keys).filter {
                    val ic1 = innerClasses1[it]
                    val ic2 = innerClasses2[it]
                    ic1 != null && !ic1.access.isSynthetic() ||
                            ic2 != null && ic2.access.isSynthetic()
                }
        val innerClassNames1 = innerClasses1.keys.filter { it in relevantInnerClassNames }.sorted()
        val innerClassNames2 = innerClasses2.keys.filter { it in relevantInnerClassNames }.sorted()

        val listDiff = compareLists(innerClassNames1, innerClassNames2) ?: return

        report.addInnerClassesDiffs(
                listDiff.diff1.map {
                    innerClasses1[it]?.toInnerClassLine() ?: "---"
                },
                listDiff.diff2.map {
                    innerClasses2[it]?.toInnerClassLine() ?: "---"
                }
        )
    }

    private fun InnerClassNode.toInnerClassLine(): String =
            "INNER_CLASS $name $outerName $innerName ${access.toString(2)} ${access.classFlags()}"
}

