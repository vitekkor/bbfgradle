// Original bug: KT-2831

fun foo(): Nothing = throw Exception()

fun bar(x: Any): Int {
  return when(x) {
    is String -> 0
    is Int -> 1
    else -> foo()
  }
}

fun main(args: Array<String>) {
    bar(3)
}
