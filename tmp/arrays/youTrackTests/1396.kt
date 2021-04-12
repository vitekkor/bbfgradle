// Original bug: KT-19123

fun innerPlain(f: () -> Int = { 1 }) = f()
inline fun innerInline(f: () -> Int = { 2 }) = f()
fun outerCall(p: Int) {}
fun main(args: Array<String>) {
    outerCall(innerPlain()) // Breakpoint.
    outerCall(innerInline())
    outerCall(innerInline() { 3 })
} 