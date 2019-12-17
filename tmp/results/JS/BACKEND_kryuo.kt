class Rgba(val g: Int) {
val value: Int get() = 1
    inline val b: Int get() = value and 1 and b shr g shr value
}