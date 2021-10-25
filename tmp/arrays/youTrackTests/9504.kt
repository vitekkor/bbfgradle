// Original bug: KT-13083

fun fib(n: Int): Int {
    var a = 0
    var b = 1

    var temp = 0
    for (i in 0 until n) {
        temp = a
        a = b
        b += temp
    }
    return a
}
