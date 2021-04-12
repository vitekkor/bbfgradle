// Original bug: KT-24460

sealed class X {
  internal abstract fun overridableMethod(x: Int): Int

  abstract class Y : X() {
    override fun overridableMethod(x: Int): Int = 1
  }

  class Z : Y() {
    override fun overridableMethod(x: Int): Int =
      if (x > 0) x
      else super.overridableMethod(x)
  }
}

fun get() : X? {
  return X.Z()
}

fun test() {
  get()?.overridableMethod(2)
}
