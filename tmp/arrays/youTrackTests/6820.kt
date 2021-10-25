// Original bug: KT-10988

class Y
class X {
    fun Y.fn() {}
    companion object {
        fun exec() = X().apply { 
            with(Y()) {
                fn() // error, fn is inaccessible, use inner
                this@with.fn() // ok
            }
        }
    }
}
