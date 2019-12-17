class Rgba(val value: Int) {
val g: Int get() = value shr 1 and 1
    inline val b: Int get() = value shr g and b
}