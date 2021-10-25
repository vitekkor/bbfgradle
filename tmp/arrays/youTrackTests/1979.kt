// Original bug: KT-22538

sealed class Foo {
  object Bar: Foo() {
    val test: String = ""
  }
}

fun testFunction(foo: Foo) {
  val x = when(foo) {
    is Foo.Bar -> foo.test
  }
}
