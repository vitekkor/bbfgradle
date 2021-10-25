// Original bug: KT-40451

open class Base
open class Derived : Base()
class Test {
    fun <T : Base> test(p: T) {}
    fun <T : Base> test2(p: T): T {
        return Base() as T //!!!
    }   
    
     fun foo() {
        test(test2(Derived()))
    }
}

fun main() {
    Test().foo()
}
