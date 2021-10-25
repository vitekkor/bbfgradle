// Original bug: KT-9850

abstract class Base {
  abstract fun <T> foo(list: List<T>) where T : Number, T : Comparable<T>
}

class Derived : Base() {
  override fun <T> foo(list: List<T>) where T : Number, T : Comparable<T> {
    println("'foo' overrides nothing")
  }
}
