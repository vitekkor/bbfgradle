// Original bug: KT-8700

enum class A { V }

fun main(args: Array<String>) {
  val a: A = A.V
  val b: Boolean
  when (a) {
    A.V -> b = true
  }
  println(if (b) "true" else "false")
}
