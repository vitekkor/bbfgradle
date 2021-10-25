// Original bug: KT-22786

fun foo(x: Int): Int = when (x) {
//    0 -> return 0 // Compile error: returns are not allowed for function with expression body
    1 -> 1
    else -> 42
}

fun bar(x: Int): Int = when (x) {
    0 -> run { return 0 }
    1 -> 1
    else -> 42
}

fun main(args: Array<String>) {
    println(bar(0) == 0) // prints true
}
