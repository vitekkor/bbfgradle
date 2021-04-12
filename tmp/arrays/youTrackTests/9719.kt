// Original bug: KT-1823

class A
fun A.foo() {}

class B {
    fun A.foo() {}

    fun test(a: A) {
        a.foo() //function B::A.foo() is preferred, but should be an ambiguity
    }
}
