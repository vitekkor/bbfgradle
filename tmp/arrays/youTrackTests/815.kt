// Original bug: KT-41255

fun main() {
    Test.method()
}

class Test {
    companion object {
        fun method() {
            var value = 0
            repeat(0) {
                println() // repeated 183 times or more
                @Suppress("NON_EXHAUSTIVE_WHEN")
                when (0) {
                    0 -> value++
                }
            }
        }
    }
}
