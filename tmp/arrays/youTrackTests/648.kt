// Original bug: KT-43349

fun <T> example(
    fun0: () -> T,
    fun1: () -> T,
    fun2: (T) -> Unit,
): T = fun1().also(fun2)

val unitRunner: (() -> Unit) -> Unit = { block -> block() }

fun main() {
    unitRunner {
        example(
            {  },
            { "hello" },
            { str -> println(str) }
        ) // `example` returns `Unit`
    }

    unitRunner {
        example(
            { "hello" },
            {  }, // type mismatch, required `String`, found `Unit`
            { str -> println(str) }
        ) // `example` returns `String`
    }
}
