// Original bug: KT-41806

open class A {
    fun foo() {
        print("foo")
    }
}
class B : A()
class C : A()

fun main() {
    var test: A = B()
    test as B
    run {
        test = C()
    }
    test.foo()
}
