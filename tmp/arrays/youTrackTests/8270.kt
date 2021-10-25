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

class Test1(delegate: Derived) : BaseImpl(), Derived by delegate
class Test2(delegate: Derived) : Derived by delegate, BaseImpl()

fun main(args: Array<String>) {
  Test1(DerivedImpl()).foo() // should print 'base'
  Test2(DerivedImpl()).foo() // should print 'derived'
}
