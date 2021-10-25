// Original bug: KT-3856

open class Factory<T>() {
    fun fill(v: T, init: T.() -> Unit) {
        v.init()
    }
} 
class A {
    var x: Int = 0
    companion object : Factory<A>() {
        fun f(a: A) {
            fill(a) {
                x = 1 // Incorrect "expression is inaccessible from inner class" error here. 
                      // Surprisingly goes away if replaced with "this.x = 1" 
            }
        }
        
    }
}
