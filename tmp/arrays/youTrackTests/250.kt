// Original bug: KT-39449

  open class A
  interface B
  class AB : A(), B
  class CD : A(), B
  fun <T> foo(t: T): Nothing? where T : A, T : B = null
  val a = listify(AB(), CD()).map(::foo)
  fun <T> listify(vararg baz: T): List<T> where T : A, T : B = listOf(*baz)
  