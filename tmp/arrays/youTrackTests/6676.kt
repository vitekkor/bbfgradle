// Original bug: KT-29530

open class B {
  open fun doB(): Unit = when (this) {
    is C -> doB() // (*)
    else -> println("B")
  }
}
class C: B() {
  override fun doB() {
    println("C")
  }
}
fun main(args: Array<String>) {
  B().doB() // Prints: B
  C().doB() // Prints: C
}
