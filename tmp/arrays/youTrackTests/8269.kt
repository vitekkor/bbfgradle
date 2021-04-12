// Original bug: KT-12531

interface Base {
  fun foo(): Unit
}

interface Derived1 : Base {
  fun someComplexMethod1()
  // ...
  fun someComplexMethodN()
}

interface Derived2 : Base {
  fun anotherComplexMethod1()
  // ...
  fun anotherComplexMethodN()
}

open class BaseImpl : Base {
  override fun foo() = Unit // dummy implementation
}

class Wrapper1(delegate: Derived1) : BaseImpl(), Derived1 by delegate
class Wrapper2(delegate: Derived2) : BaseImpl(), Derived2 by delegate
