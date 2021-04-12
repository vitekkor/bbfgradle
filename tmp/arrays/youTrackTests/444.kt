// Original bug: KT-41426

private operator fun Long?.plus(other: Long?): Long =
    (this ?: 0L) + (other ?: 0L)

private operator fun Long?.compareTo(other: Long?): Int {
    val diff = (this ?: 0L) - (other ?: 0L)
    return when {
        diff < 0L -> -1
        diff > 0L -> 1
        else -> 0
    }
}

fun main() {
    val a: Long? = null
    val b: Long? = 1
    println(a + b)
    println(a > b)
}
