// Original bug: KT-29336

package a.b.c

class MyClass {
    fun foo() {
        this.defaults<a.b.c.MyClass>()  // No intention here
    }

    private fun <T> defaults() {
    }
}
