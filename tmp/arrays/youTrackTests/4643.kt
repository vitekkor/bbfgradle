// Original bug: KT-35607

fun main() {
    withUnreachablePrintln() // breakpoint
}

fun withUnreachablePrintln() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // non-local return directly to the caller of withUnreachablePrintln()
        println(it)
    }
    println("this point is unreachable")
}
