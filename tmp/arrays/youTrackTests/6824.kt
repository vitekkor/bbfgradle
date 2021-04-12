// Original bug: KT-8767

class A {
    companion object {
        fun foo(x : A) {
            with(x) {
                bar() // Error: Expression is inaccessible from a nested class 'Companion', use 'inner' keyword to make the class inner
            }
        }
    }

    fun bar() { }
}
