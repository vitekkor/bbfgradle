// Original bug: KT-35015

package package1
object Math {
    @Deprecated("Replace", ReplaceWith("kotlin.math.cos(x)", "kotlin.math.cos"))
    fun cos(x: Double): Double = kotlin.math.cos(x)
}
