// Original bug: KT-9656

open class A {
    fun foo() {}
}

class B: A() {
    
    class D {
        init {
            with(A()) {
                foo() // wrong error: INACCESSIBLE_OUTER_CLASS_EXPRESSION 
                this.foo() // works fine
            }
        }
    }
}
