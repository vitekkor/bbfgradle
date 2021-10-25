// Original bug: KT-29822

fun test1(a: IntArray) {
    for ((i, x) in a.withIndex()) {
        println(x)
    }
}

fun test2(a: UIntArray) {
    for ((i, x) in a.withIndex()) {
        println(x)
    }
}
