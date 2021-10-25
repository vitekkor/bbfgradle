// Original bug: KT-3303

open class A() {
    fun body() {}
}

open class B() {
    val body : Function0<Unit> = {}; 
}

class C() : A() {
    fun B.test() {
        body()      // Incorrectly resolves to A's fun                                       
        this.body() // Correctly resolves to B's val
    }    
}
