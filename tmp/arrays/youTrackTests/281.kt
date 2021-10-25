// Original bug: KT-22170

class Test {
  fun test() {
    fooStatic("Second", "First")
    fooSimple("Second", "First")
  }

  companion object {
    @JvmStatic
    fun fooStatic(second: String, first: String) { }

    fun fooSimple(second: String, first: String) { }
  }
}
