// Original bug: KT-24807

fun function1(x: Boolean) {
  if (x) {
    println("x is true")
  } else if (!x) {
    println("x is false")
  } else { // "else" can not happen, a warning woud be nice.
    throw Exception("This should never happen")
  }
}


fun function2(x: Boolean) {
  when {
    x -> println("x is true")
    !x -> println("x is false")
    else -> throw Exception("This should never happen") // "else" can not happen, a warning woud be nice.
  }
}
