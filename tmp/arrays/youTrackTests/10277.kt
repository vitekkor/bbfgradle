// Original bug: KT-5786

fun main(args: String) {
  run {
    test("broken") // Works outside closure
    test("ok", 200)
  }
}

private fun test(arg1: String, default: Int = 0) = Unit
