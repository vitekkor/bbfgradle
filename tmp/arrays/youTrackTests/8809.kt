// Original bug: KT-10403

inline fun <reified T : Comparable<T>> max(a: T, b: T) = if (a > b) a else b

fun foo() {
  val bar = max(5.0, 10.0) // boxes `5.0` and `6.0` when it's not really necessary
}
