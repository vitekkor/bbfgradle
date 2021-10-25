// Original bug: KT-13681

fun doit(x: Any) {
  val result: String = when (x) {
    is Foo ->
      when (x) { // error: 'when' expression must be exhaustive
        is Foo.Bar -> "foo.bar"
      }
    else -> "else"
  }

}

sealed class Foo {
  class Bar : Foo()
}
