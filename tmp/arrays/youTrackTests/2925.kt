// Original bug: KT-28064

fun foo(): Any {
    return {}
}

fun main(args: Array<String>) {
    val x = foo()
    val y = foo()
    println(x === y)  // false if foo is inline, true if non-inline
}
