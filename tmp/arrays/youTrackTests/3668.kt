// Original bug: KT-37854

class Foo {
  val x: Int = 4
  val y: String = "foo"

  fun foo() {
    println("$x $y ${"42".toInt() + x}") // Alt +click doesn't work on this string literal
  }
}
