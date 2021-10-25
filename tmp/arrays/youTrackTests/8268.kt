// Original bug: KT-12531

interface Base {
  fun foo(): Unit 
}

interface Derived : Base

open class BaseImpl : Base {
  override fun foo() = println("base")
}

class DerivedImpl : BaseImpl(), Derived {
  override fun foo() = println("derived")
}

class Test(delegate: Derived) : BaseImpl(), Derived by delegate

fun main(args: Array<String>) {
  Test(DerivedImpl()).foo() // prints 'derived'
}
