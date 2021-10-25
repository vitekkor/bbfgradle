// Original bug: KT-16350

fun main(args: Array<String>) {
    val active = arrayOf(false, true, false)
    val values = arrayOf(1, null, 3)
    for (i in 0..2) {
        if (!active[i] && check(i, values[i]!!)) {}
    }
}

fun check(index: Int, value: Int): Boolean {
    println("" + index + " : " + value)
    return true
}
