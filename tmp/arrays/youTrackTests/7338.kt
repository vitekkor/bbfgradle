// Original bug: KT-21354

fun test(): Int {
    var xs = intArrayOf(1, 2, 3)
    var y = 0
    for (x in xs) {
        y = y * 10 + x
        xs = intArrayOf(4, 5, 6)
    }
    return y
}
