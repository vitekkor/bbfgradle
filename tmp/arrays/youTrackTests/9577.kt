// Original bug: KT-11998

fun main(args: Array<String>) {
  val b: Boolean? = true
  if (b != null) {
    // Smart cast of 'b' to Boolean
    if (b) {
      println("b was true")
    }
  }
}
