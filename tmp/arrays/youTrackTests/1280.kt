// Original bug: KT-24305

fun main(args: Array<String>) {
  val n = 1
  //  repeat(1) {
  class Foo {
    constructor() { println("in constructor") }
    fun bar() = n + 1
  }
  println(Foo::class.java.getDeclaredConstructor())
//  }
}
