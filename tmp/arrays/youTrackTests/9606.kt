// Original bug: KT-6203

class TestClass {
    object a {}

    companion object {
      object b {
      }
    }
    
    fun test() {
        a
        b
    }
}

fun test() {
  TestClass.a
  TestClass.Companion.b
}
