// Original bug: KT-36038

open class A
class B : A()
class C : A()
class D : A()
class E : A()

private fun A.foo() =
    this is B ||
            this is C ||
            this is D ||
            this is E && 5.run {
        true // incorrect indent
    }
