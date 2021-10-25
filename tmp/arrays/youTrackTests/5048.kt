// Original bug: KT-34613

import kotlin.test.assertFailsWith

interface Foo<T : Any>
data class FooImpl<T : Any>(val a: T) : Foo<T> {
  fun fail() {
    error("eh")
  }
}

fun foo() {
  val foo: Foo<Any> = FooImpl(a = 2)

  (foo as FooImpl).let {
    assertFailsWith<IllegalStateException> {
      it.fail()
    }
  }
}
