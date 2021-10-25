// Original bug: KT-7437

open class A {
    protected fun foo() { }

    fun bar(x: B) {
        x.foo() // Error:(6, 11) Kotlin: Cannot access 'foo': it is 'protected' in 'B'
    }
}

class B : A()
