// Original bug: KT-41067

inline fun test(
  l1: (Int) -> Int,
  l2: (Int) -> Int,
  l3: (Int) -> Int = { it + 1 }
): Int = 42

object Temp {
  fun add(x: Int) = x + 1

  fun temp() {
    test(::add, { it })
    test({ it }, { it }, l3 = { it })
  }
}

fun main() {
  Temp.temp()
}
