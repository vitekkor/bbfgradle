// Original bug: KT-17776

class Test {
  inner class Inner {
    fun bar() {
      listOf("").forEach {
        foo(it)
      }
    }

    fun foo(v: String) {
      bizz(v)
    }
  }
  
  fun bizz(v:String) {
    v.toString()
  }
}
