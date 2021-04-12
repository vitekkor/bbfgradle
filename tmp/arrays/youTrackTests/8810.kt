// Original bug: KT-10403

inline fun <reified T : Comparable<T>> max(a: T, b: T) = a // obviously incorrect, but illustrates the point
fun foo() {
  val bar = max(5.0, 10.0) // no boxing occurs
}
