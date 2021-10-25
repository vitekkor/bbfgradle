// Original bug: KT-22663

package test

class Foo {
    fun bar(obj: Any) {
        with(obj) {
            Runnable {
                obj.hashCode()
                Runnable { }
            }
        }
    }
}
