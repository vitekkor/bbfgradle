package com.stepanov.bbf.bugfinder.abiComparator.reports

import java.io.PrintWriter

interface ComparisonReport {
    fun isEmpty(): Boolean
    fun write(output: PrintWriter)
}

fun ComparisonReport.isNotEmpty() = !isEmpty()