// Original bug: KT-6624

class Example {

  var foo = createFoo()

  var bar = 3

  fun createFoo(): String {
    return bar.toString()
  }
}

fun main() {
  val x = Example()
  print(x.foo)
}
