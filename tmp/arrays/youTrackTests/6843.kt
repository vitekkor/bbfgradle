// Original bug: KT-13716

open class A() {
  open fun compareTo(o: Any): Int = 0
}
class B() : A(), Comparable<B> {
  override fun compareTo(o: B): Int = 0
}

fun main(args: Array<String>) {
  println(B().compareTo(Object()))
}
