// Original bug: KT-36998

// Some library
interface Base {
    fun foo() {}
}

interface Left : Base 

interface Right : Base

// Some client of this library
class KClass : Left, Right {    
    // override fun foo() {
    //     implicitly generated call to Left.DefaultImpls.foo(this)
    // }
}
