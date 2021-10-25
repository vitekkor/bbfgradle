// Original bug: KT-29802

fun main() {
    test<Int>()
}

inline fun <reified T> test() {
    runWithReified<String> {
        val t1 = run0 { T::class }
        val t2 = T::class

        if (t1 != t2) throw AssertionError("$t1 != $t2")
    }
}

inline fun <reified T> runWithReified(block: () -> Unit): Unit = block()

fun run0(block: () -> Any): Any = block()
