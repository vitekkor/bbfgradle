// Original bug: KT-31832

class Test {
  init {
    throw IllegalStateException("boo")
    println("nope") // "unreachable code" warning missing
  }

  fun foo() {
    throw IllegalStateException("boo")
    println("nope") // "unreachable code" warning is shown as expected
  }
}
