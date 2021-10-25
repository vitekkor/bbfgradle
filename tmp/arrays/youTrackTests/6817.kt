// Original bug: KT-10564

object Util {
    // Updated at application start
    var density: Float = 2.0f
}

val Int.dp: Int
    get() = (this * Util.density).toInt()
val Float.dp: Float
    get() = this.toFloat() * Util.density
val Double.dp: Double
    get() = this * Util.density
