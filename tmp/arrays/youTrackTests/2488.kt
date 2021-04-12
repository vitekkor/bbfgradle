// Original bug: KT-29083

open class B {
  open fun doB(): Unit = when (this) {
    is C -> doB() // This is treated as a recursive call, but should be smart casted and refer to doB() on line #9
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
