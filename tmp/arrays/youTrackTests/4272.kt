// Original bug: KT-37183

fun profile(f: () -> Unit) {
  val s = System.currentTimeMillis()
  for (i in 0..1000000) { f() }
  val e = System.currentTimeMillis()
  println(e - s)
}

//Copied from standard Kotlin function
fun DoubleArray.maxBy2(): Double? {
  if (isEmpty()) return null
  var maxElem = this[0]
  var maxValue = maxElem
  for (i in 1..lastIndex) {
    val e = this[i]
    val v = e //Except this line, was selector(e)
    if (maxValue < v) {
      maxElem = e
      maxValue = v
    }
  }
  return maxElem
}

fun main() {
  val doubleArray = DoubleArray(1000)

  profile {
    doubleArray.maxBy { it } //Prints 1736 ms
  }
  profile {
    doubleArray.maxBy2() //Prints 324 ms
  }
}

