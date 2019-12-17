class Rgba(val g: Int) {
val value: Int get() = value
    inline val b: Int get() = 1 shr value and g and b shr 1
}