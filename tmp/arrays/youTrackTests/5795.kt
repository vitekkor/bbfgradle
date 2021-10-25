// Original bug: KT-24585

class B {}

data class A(val data:String)

val A.foo: String get() {
  val b = B()
  b.apply {
    val x = data;
  }
  return ""
}

fun main(args: Array<String>) {
  println(A("").foo)
}
