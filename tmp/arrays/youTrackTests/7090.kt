// Original bug: KT-28819

fun function1(x: String) {
  if (x == "good") {
    println("x is good")
  } else if (x == "bad") {
    println("x is bad")
  } else {
    IllegalArgumentException("This should never happen")
  }
}
