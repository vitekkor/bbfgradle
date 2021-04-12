// Original bug: KT-22170

class Test {
  fun test() {
    fooStatic("First", "Second")
    fooSimple("First", "Second")
  }

  companion object {
    @JvmStatic
    fun fooStatic(first: String, second: String) { }

    fun fooSimple(first: String, second: String) { }
  }
}
