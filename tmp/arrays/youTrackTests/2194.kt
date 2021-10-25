// Original bug: KT-34907

fun main() {
  Bar1(FooBar(1.0)).doIt(Bar2(FooBar(17.0)))
}

inline class FooBar(val v: Double)

open class Bar1(open val foo: FooBar? = null) {
  fun doIt(other: Bar1) {
    this.foo == other.foo
  }
}

class Bar2(override val foo: FooBar) : Bar1() {
}

