// Original bug: KT-29239

fun main(args: Array<String>) {
    C().bar()
}

class C : B , A

interface B {
    fun bar() {
        println("B.bar")
        foo()
    }

    private fun foo() {
        println("B.foo")
    }

}

interface A {
    fun foo() {
        println("A.foo")
    }
}
