// Original bug: KT-25075

fun requireN(n: Int) {
    require(n in 1..100) {
        "$n must be in range 1..100" // breakpoint
    } 
}

fun main(args: Array<String>) {
    requireN(0)
}
