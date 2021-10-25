// Original bug: KT-28931

fun bugTest() {
  val nonNullValue = try {
    val dummy = thisFunctionReturnsANullableInt()
    if (dummy == null) {
      println("Looks like something is null, I should return.")
      return
    }
    dummy
  }
  catch (e: Exception) {
    println("I do nothing, except return early.")
    return
  }

  val sum = nonNullValue + 1
  println("My non-null value + 1 is $sum")
}

fun thisFunctionReturnsANullableInt(): Int? {
    return 42
}
