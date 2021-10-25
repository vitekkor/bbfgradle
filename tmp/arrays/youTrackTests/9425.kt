// Original bug: KT-13381

interface ReportTestMixin {
    private val log: String get() = ""
}

interface GanttReportTestMixin : ReportTestMixin {
}

class GanttReportDataTest : GanttReportTestMixin {
}

