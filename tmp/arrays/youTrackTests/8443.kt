// Original bug: KT-17929

fun main(args: Array<String>) {
  var s: String?
  s = null
  try {
    s = "Test"
  } catch (ex: Exception) {}
  println(s)
  if (s != null) {
    println("Always false?")
  }
}
