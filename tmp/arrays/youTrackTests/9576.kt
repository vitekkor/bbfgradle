// Original bug: KT-11998

data class Foo(val bar: Boolean?)

fun use(bar: Boolean) {}

fun main(args: Array<String>) {
  val f = Foo(true)
  if (f.bar != null) {
    use(f.bar) // Smart cast to Boolean
  }
}
