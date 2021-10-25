// Original bug: KT-7406

class A(val x: Int) {
  val y: Int = if (true) x else throw Exception()
}

class B(val x: Int) {
  val y: Int = try { x } catch (e: Exception) { -1 }
}

fun main(args : Array<String>) {
  val a = A(10)
  println(a.y)
  val b = B(10)
  println(b.y)
}
