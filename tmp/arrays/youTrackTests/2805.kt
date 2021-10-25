// Original bug: KT-21797

fun main(args: Array<String>) {
    println("Start")
    777.fortyTwo { 42 } // breakpoint
    println("Finish")
}

fun Int.fortyTwo(f: () -> Int): Int {
    println("Lambda is called with $this")
    return f()
}
