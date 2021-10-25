// Original bug: KT-26132

fun <T: Any> T.test() = 1
fun <T: Any> Pair<String, T>.test() = "2"

fun main(args: Array<String>) {
  val a: Int = 1.test()
  val b: String = ("1" to 1).test()
}
