// Original bug: KT-24790

interface A

data class B<out T : A>(val a: T)

fun main(args: Array<String>) {
  val b1 = B(object : A {})
  val b2 = B(object : A {})
  println(b1.hashCode() == b2.hashCode())
}
