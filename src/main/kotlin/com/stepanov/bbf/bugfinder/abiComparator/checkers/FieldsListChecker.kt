package com.stepanov.bbf.bugfinder.abiComparator.checkers

import com.stepanov.bbf.bugfinder.abiComparator.fieldFlags
import com.stepanov.bbf.bugfinder.abiComparator.isSynthetic
import com.stepanov.bbf.bugfinder.abiComparator.listOfNotNull
import com.stepanov.bbf.bugfinder.abiComparator.reports.ClassReport
import com.stepanov.bbf.bugfinder.abiComparator.tasks.fieldId
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldNode

class FieldsListChecker : ClassChecker {
    override val name = "class.fields"

    override fun check(class1: ClassNode, class2: ClassNode, report: ClassReport) {
        val fields1 = class1.fields.listOfNotNull<FieldNode>().associateBy { it.fieldId() }
        val fields2 = class2.fields.listOfNotNull<FieldNode>().associateBy { it.fieldId() }

        val relevantFieldIds = fields1.keys.union(fields2.keys)
                .filter {
                    val field1 = fields1[it]
                    val field2 = fields2[it]
                    !(field1 != null && !field1.access.isSynthetic() ||
                            field2 != null && !field2.access.isSynthetic())
                }.toSet()

        val fieldIds1 = fields1.keys.intersect(relevantFieldIds).sorted()

        val fieldIds2 = fields2.keys.intersect(relevantFieldIds).sorted()

        val listDiff = compareLists(fieldIds1, fieldIds2) ?: return
        report.addFieldListDiffs(
                listDiff.diff1.map { it.toFieldWithFlags(fields1) },
                listDiff.diff2.map { it.toFieldWithFlags(fields2) }
        )
    }

    private fun String.toFieldWithFlags(fields: Map<String, FieldNode>): String {
        val field = fields[this] ?: return this
        return "$this ${field.access.fieldFlags()}"
    }
}