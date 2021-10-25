// Original bug: KT-1864


open class A {
  open fun foo(a : Int = 1) = a 
}  

class B : A() {
  override fun foo(a : Int) = a + 1
}

fun main(args : Array<String>) {
  println(B().foo())
}
