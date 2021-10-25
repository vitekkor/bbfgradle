// Original bug: KT-40820

interface Foo {
  fun bar(): Bar
}

interface Bar {
  fun foo(): Foo
}

class RecursiveFoo : Foo {
  override fun bar(): Bar {
    val me = this
    return object : Bar {
      override fun foo() = me
    }
  }
}
