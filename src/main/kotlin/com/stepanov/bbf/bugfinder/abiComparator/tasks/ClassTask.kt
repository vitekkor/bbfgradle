package com.stepanov.bbf.bugfinder.abiComparator.tasks

import com.stepanov.bbf.bugfinder.abiComparator.classFlags
import com.stepanov.bbf.bugfinder.abiComparator.isSynthetic
import com.stepanov.bbf.bugfinder.abiComparator.listOfNotNull
import com.stepanov.bbf.bugfinder.abiComparator.reports.ClassReport
import com.stepanov.bbf.bugfinder.abiComparator.tag
import com.stepanov.bbf.bugfinder.abiComparator.tasks.FieldTask
import com.stepanov.bbf.bugfinder.abiComparator.tasks.MethodTask
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldNode
import org.objectweb.asm.tree.MethodNode

class ClassTask(
    private val checkerConfiguration: CheckerConfiguration,
    private val class1: ClassNode,
    private val class2: ClassNode,
    private val report: ClassReport
) : Runnable {

    override fun run() {
        addClassInfo()

        for (checker in checkerConfiguration.enabledClassCheckers) {
            checker.check(class1, class2, report)
        }

        checkMethods()
        checkFields()
    }

    private fun addClassInfo() {
        report.info {
            tag("p") {
                tag("b", report.header1)
                println(": ${class1.access.classFlags()}")
            }
            tag("p") {
                tag("b", report.header2)
                println(": ${class2.access.classFlags()}")
            }
        }
    }

    private fun checkMethods() {
        val methods1 = class1.methods.listOfNotNull<MethodNode>().associateBy { it.methodId() }
        val methods2 = class2.methods.listOfNotNull<MethodNode>().associateBy { it.methodId() }


        val commonIds = methods1.keys.intersect(methods2.keys).sorted()
        for (id in commonIds) {
            val method1 = methods1[id]!!
            val method2 = methods2[id]!!
            if (method1.access.isSynthetic() && method2.access.isSynthetic()) continue
            val methodReport = report.methodReport(id)
            MethodTask(checkerConfiguration, method1, method2, methodReport).run()
        }
    }

    private fun checkFields() {
        val fields1 = class1.fields.listOfNotNull<FieldNode>().associateBy { it.fieldId() }
        val fields2 = class2.fields.listOfNotNull<FieldNode>().associateBy { it.fieldId() }

        val commonIds = fields1.keys.intersect(fields2.keys).sorted()
        for (id in commonIds) {
            val field1 = fields1[id]!!
            val field2 = fields2[id]!!
            if (field1.access.isSynthetic() && field2.access.isSynthetic()) continue
            val fieldReport = report.fieldReport(id)
            FieldTask(checkerConfiguration, field1, field2, fieldReport).run()
        }
    }
}

fun MethodNode.methodId() = "$name$desc"

fun FieldNode.fieldId() = "$name:$desc"
