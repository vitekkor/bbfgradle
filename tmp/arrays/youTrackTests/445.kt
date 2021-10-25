// Original bug: KT-41426

private inline class LongNull(val value: Long?)

private operator fun LongNull.compareTo(other: LongNull): Int {
    val diff = (this.value ?: 0L) - (other.value ?: 0L)
    return when {
        diff < 0L -> -1
        diff > 0L -> 1
        else -> 0
    }
}
