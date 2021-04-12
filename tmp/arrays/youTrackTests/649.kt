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
                    { },
                    { "hello" },
                    { str -> println(str) }
                )
            },
            {
                example(
                    { "hello" },
                    { "hello" },
                    { str -> println(str) }
                )
            },
            { x -> println(x) } // it's not so easy to understand what type of `x` is here
        )
    }
}
