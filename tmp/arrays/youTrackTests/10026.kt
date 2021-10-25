// Original bug: KT-6744

open class A {
    class B : A() {
        val a = 1
    }

    fun foo() {
        if (this is B) println(a)
    }
}


fun main(args: Array<String>) {
    A()
}
