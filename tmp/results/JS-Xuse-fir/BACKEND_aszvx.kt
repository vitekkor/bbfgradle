class Rgba(val g: Int) {
val value: Int get() = 1
    inline val b: Int get() = b shr 1 shr value and g and value
}