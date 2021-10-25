// Original bug: KT-10670

fun regularDefault(p: Int = 1, f: (Int) -> Unit) {
    f(p) // line breakpoint
}
inline fun inlineNoDefault(p: Int, f: (Int) -> Unit) { f(p) } // line breakpoint
inline fun inlineDefault(p: Int = 1, f: (Int) -> Unit) { f(p) } // line breakpoint

fun main(args: Array<String>) {
    regularDefault { println(it) }
    inlineNoDefault(1) { println(it) }
    inlineDefault { println(it) } // Problematic call.
    inlineDefault(1) { println(it) }
} 