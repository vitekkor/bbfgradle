// Original bug: KT-29083

open class B {
  open fun doB(): Unit = when (this) {
    is C -> (this as C).doB() // Now doB() is resolved correctly, but Kotlin plugin incorrectly reports "No cast needed"
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
