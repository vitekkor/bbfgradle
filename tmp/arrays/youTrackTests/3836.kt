// Original bug: KT-4207

fun String.foo() = { this }

class A {
  fun foo() = { this }
  val ok = "OK"
}

fun main(args : Array<String>) {
  println("OK".foo()())
  println(A().foo()().ok)
}
