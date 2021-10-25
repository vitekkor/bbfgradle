// Original bug: KT-31548

data class Report(val content: String)
typealias Reporter = (Report) -> Unit

fun reporterA(report:Report) { println("A: $report")}
fun reporterB(report:Report) { println("B: $report")}

class CompositeReporter(val delegates:Array<out Reporter>):Reporter {
    override fun invoke(report: Report) {
       delegates.forEach { reporter -> reporter(report) }
    }
}
fun main(){
    val reporter = CompositeReporter(arrayOf(::reporterA, ::reporterB))
    reporter(Report("Content"))
}
