// Original bug: KT-36077

fun interface Zap {
  fun zap(i: Int)
}

fun useZap(z: Zap) {}

fun foo(vararg xs: Int) = 42

fun testExplicitConversion() = Zap(::foo)

fun testImplicitConversion() = useZap(::foo)
