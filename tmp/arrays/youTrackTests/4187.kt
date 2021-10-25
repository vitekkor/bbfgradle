// Original bug: KT-36298

val <A> (()->A).toNullVal: (()->A?) get() = { null }
fun <A> (()->A).toNullFun(): (()->A?) = { null }

fun main() {
  val result1 = "42"::toDouble.toNullFun()() // deduced as Double?
  val result2 = "42"::toDouble.toNullVal() // deduced as A?
  val result3 = { 0.42 }.toNullVal() // deduced as A?
  val result4: Double? = { 0.42 }.toNullVal() // error: Type mismatch. Required: Double? Found: A?
}
