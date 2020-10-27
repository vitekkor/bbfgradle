package com.stepanov.bbf.bugfinder.abiComparator.checkers

import com.github.difflib.DiffUtils
import com.stepanov.bbf.bugfinder.abiComparator.metadata.renderKotlinMetadata
import com.stepanov.bbf.bugfinder.abiComparator.reports.ClassReport
import com.stepanov.bbf.bugfinder.abiComparator.reports.TextDiffEntry
import org.objectweb.asm.tree.ClassNode

class KotlinMetadataChecker : ClassChecker {
    override val name = "class.metadata"

    override fun check(class1: ClassNode, class2: ClassNode, report: ClassReport) {
        val metadata1 = class1.renderKotlinMetadata() ?: ""
        val metadata2 = class2.renderKotlinMetadata() ?: ""
        if (metadata1 == metadata2) return

        val patch = DiffUtils.diff(metadata1, metadata2, null)
        for (delta in patch.deltas) {
            report.addMetadataDiff(
                TextDiffEntry(
                    delta.source.lines.toList(), delta.target.lines.toList()
            )
            )
        }
    }
}