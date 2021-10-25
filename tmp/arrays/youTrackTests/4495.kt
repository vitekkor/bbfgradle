// Original bug: KT-35791

fun main() {
    val a = 42 // breakpoint
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // non-local return directly to the caller of withUnreachablePrintln()
        println(it)
    }
}
