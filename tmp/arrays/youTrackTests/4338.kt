// Original bug: KT-33906

fun maxOf(a: Int, b: Int, c: Int, vararg other: Int): Int {
    var max = maxOf(a, b, c)
    for (i in 0..other.lastIndex) {
        val e = other[i]
        if (max < e) max = e
    }
    return max
}
