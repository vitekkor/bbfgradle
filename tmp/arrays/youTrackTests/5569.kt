// Original bug: KT-20773

interface I {
  fun getFoo(): String
}

class A(i: I) {
  init {
    val foo: String = i.getFoo()
    bar(foo)
  }

  private fun bar(value: String?) {
    value?.let { println(it) }
  }
}
