// Original bug: KT-27417

interface I {
    val overrideMe: Double
    fun someFun(that: I) = this.overrideMe + that.overrideMe
}

inline class C(override val overrideMe: Double) : I

fun main(args: Array<String>) {
    val x = C(1.0)
    val y = C(2.0)
    val z = x.someFun(y)
}
