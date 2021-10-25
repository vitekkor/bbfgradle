// Original bug: KT-42020

open class Foo1<T> {
  open fun foo(a: T) {}
  open fun foo(a: String) {}
}

class Foo2 : Foo1<String>() {
  override fun foo(a: String) {} // overrides both Foo1::foo(T) and Foo1::foo(String), with bridge from Foo1::foo(T)
}
