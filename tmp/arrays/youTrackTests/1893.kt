// Original bug: KT-42874

fun main() {
    val answer: List<Int> = listOf()
    answer.foo { it }
}

@Deprecated("", ReplaceWith("bar(selector)"))
fun List<out Int>.foo(selector: (Int) -> Unit): Nothing = bar(selector)
fun List<Int>.bar(selector: (Int) -> Unit): Nothing = TODO()
fun Iterable<Int>.bar(selector: (Int) -> Unit) = Unit
