// Original bug: KT-17749

private class B : A() {
    fun testB() = foo()
    
    private fun foo() {
        println("B.foo")
    }
}

private open class A {
    fun testA() = foo()
    
    private fun foo() {
        println("A.foo")
    }
}

fun main(a: Array<String>) {
    A().testA()
    B().testA()
    B().testB()
}
