package com.stepanov.bbf.bugfinder.abiComparator.reports

import com.stepanov.bbf.bugfinder.abiComparator.escapeHtml
import com.stepanov.bbf.bugfinder.abiComparator.table
import com.stepanov.bbf.bugfinder.abiComparator.tableData
import com.stepanov.bbf.bugfinder.abiComparator.tableHeader
import java.io.PrintWriter

fun String.tag(tagName: String) = "<$tagName>$this</$tagName>"

fun PrintWriter.propertyDiffTable(header1: String, header2: String, propertyDiffs: List<NamedDiffEntry>) {
    if (propertyDiffs.isNotEmpty()) {
        table {
            tableHeader("Property", header1, header2)
            for (pd in propertyDiffs) {
                tableData(pd.name, pd.value1.tag("code"), pd.value2.tag("code"))
            }
        }
        println("&nbsp;")
    }
}

fun PrintWriter.annotationDiffTable(header1: String, header2: String, annotationDiffs: List<NamedDiffEntry>) {
    if (annotationDiffs.isNotEmpty()) {
        table {
            tableHeader("Annotation", header1, header2)
            for (ad in annotationDiffs) {
                tableData(ad.name, ad.value1.escapeHtml().tag("code"), ad.value2.escapeHtml().tag("code"))
            }
        }
        println("&nbsp;")
    }
}

fun PrintWriter.listDiff(header1: String, header2: String, listDiffs: List<DiffEntry>) {
    if (listDiffs.isNotEmpty()) {
        table {
            tableHeader(header1, header2)
            for (me in listDiffs) {
                tableData(me.value1.escapeHtml().tag("code"), me.value2.escapeHtml().tag("code"))
            }
        }
        println("&nbsp;")
    }
}