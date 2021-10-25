// Original bug: KT-19975

fun nonInlineDefault(fn: () -> Unit = { println() }) {
    fn() // Breakpoint A.
}
inline fun inlineDefault(fn: () -> Unit = { println() }) {
    fn() // Breakpoint B.
}
fun main(args: Array<String>) {
    nonInlineDefault()
    inlineDefault()
} 