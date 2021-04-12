// Original bug: KT-354

open class Foo() {
  open class Bar() {

  }

  val b = Bar()

  class X() : Bar() {}
}

class X() : Foo() {
    class Y() : Bar() {} // ERROR, but shouldn't be
}
