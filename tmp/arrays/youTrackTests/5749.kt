// Original bug: KT-19976

inline fun stepOutMe(fn: () -> Unit = { println() }) {
    fn() // Breakpoint.
    println("More important code.") // Line N.
}

fun main(args: Array<String>) {
    stepOutMe()
    stepOutMe { println() }
} 