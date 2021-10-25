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
            {
                example(
                    { "hello" },
                    { "hello" },
                    { str -> println(str) }
                )
            },
            {
                example(
                    { },
                    { "hello" },
                    { str -> println(str) }
                )
            },
            { x -> println(x) } // `x` turns out `Any` instead of `Unit`
        )
    }
}
