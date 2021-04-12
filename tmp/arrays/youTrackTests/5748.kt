// Original bug: KT-18574

fun <X> nonInlineXU(p: X, f: (X) -> Unit = {
    println(p.toString()) // Breakpoint NXU.
}): Unit = f(p)
inline fun <X> inlineXU(p: X, f: (X) -> Unit = {
    println(p.toString()) // Breakpoint XU.
}): Unit = f(p)
inline fun inlinePU(p: String, f: () -> Unit = {
    println(p) // Breakpoint PU.
}): Unit = f()

fun main(args: Array<String>) {
    nonInlineXU("abc")
    inlineXU("abc")
    inlinePU("abc")
} 