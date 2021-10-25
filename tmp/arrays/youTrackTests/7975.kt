// Original bug: KT-22741

class Something {
    fun nullable(): Int? { TODO("return int or null") }
}
fun Something?.nullable(value: Int): Int? =
    if (this == null) value else nullable()
