// Original bug: KT-32579

open class A {
    fun toto() {
        if (this is B) {
            C()
        }
    }
}

class B: A() { inner class C }

fun main(args: Array<String>) {
    B().toto()
}
