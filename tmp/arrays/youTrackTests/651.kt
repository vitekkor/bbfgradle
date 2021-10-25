// Original bug: KT-43349

fun <T> example(
    fun1: () -> T,
    fun2: (T) -> Unit
): T = fun1().also(fun2)

val unitRunner: (() -> Unit) -> Unit = { block -> block() }

fun main() {
    unitRunner {
        example<String>(
            { "hello" },
            { str -> println(str) }
        )
    }
}
