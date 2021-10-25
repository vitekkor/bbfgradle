// Original bug: KT-44524

interface I {
  val x: String
  val y: Int
  operator fun component1(): String = x
  operator fun component2(): Int = y
}

fun introduceVariable(instance: I) {
  val (x, y) = instance // expected result
  val (s, i) = instance // actual result
}
