// Original bug: KT-33155

class Foo {
  fun foo() {
    "crashMe".let {
      object : Any() {
        override fun toString() = this@Foo.toString()
      }
    }.toString()
  }
}

fun main() {
  Foo().foo()
}
