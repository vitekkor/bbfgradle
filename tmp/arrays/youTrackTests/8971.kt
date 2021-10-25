// Original bug: KT-17507

interface Reporter {
    fun report(d: String)
}

class WrappedReporter(val r: Reporter) : Reporter by r

fun report(ds: List<String>) {
    var reported = false // VARIABLE_WITH_REDUNDANT_INITIALIZER
    val myReporter = object : Reporter {
        override fun report(d: String) {
            reported = true
        }
    }

    val wrapper = WrappedReporter(myReporter)
    for (d in ds) {
        reported = false
        wrapper.report(d)
        if (!reported) {
            TODO()
        }
    }
}
