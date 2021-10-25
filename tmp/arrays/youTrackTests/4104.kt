// Original bug: KT-26264

fun main(args: Array<String>) {
  println(listOf(1, 2).render())
  println(listOf(1, 2).len())
}

fun Any.render() = when (this) {
  is List<*> -> /* FAILS without */  this. /* here */ joinToString(",", "[", "]") { it?.toString() ?: "" }
  else -> toString()
}

fun Any.len() = when (this) {
  is List<*> -> size
  else -> -1
}
