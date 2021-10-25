// Original bug: KT-20054

inline fun Double.foo(f: (Double) -> Double): Double {
    return f(this)
}

fun bar() {
    0.5.foo(if (true) { a: Double -> Math.ceil(a) } else { a: Double -> Math.floor(a) })
}
