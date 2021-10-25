// Original bug: KT-11998

data class Foo(val bar: Boolean?)

fun main(args: Array<String>) {
  val f = Foo(true)
  if (f.bar != null) {
    if (f.bar!!) println("bar was true")
  }
}
