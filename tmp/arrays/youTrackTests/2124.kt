// Original bug: KT-42951

package exampletmp

@Deprecated("foobar")
@JvmOverloads
fun exampletmpFn(i: Int = 123) {
  exampletmpFn(i, 456)
}

fun exampletmpFn(i: Int, j: Int) {
  println(i)
}
