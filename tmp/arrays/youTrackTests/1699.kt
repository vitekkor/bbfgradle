// Original bug: KT-34665

fun box(): String {
    val maxIndexSequence = Sequence {
        object : Iterator<String> {
            var counter = Int.MAX_VALUE + 2L
            override fun hasNext(): Boolean = counter > 0
            override fun next(): String = "k".also { counter-- }
        }
    }
    try {
        for ((index, _) in maxIndexSequence.withIndex()) {
            if (index < 0) throw AssertionError("Negative index")
        }
        return "Fail: ArithmeticException should have been thrown"
    } catch (e: ArithmeticException) {
        return "OK"
    }
}
