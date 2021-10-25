// Original bug: KT-22274

class C {
    fun foo() { println("C.foo") }
    
    inner class D {
        fun foo() { println("D.foo") }
        
        fun bar() { C@this.foo() }
    }
}
