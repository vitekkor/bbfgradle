// Original bug: KT-1865

open class A {
  open fun foo(a : Int = 1) = a 
}  

class B : A() {
  override fun foo(a : Int) = a + 1
}

fun main(args : Array<String>) {
  println(B().foo()) // Should print 2
}
