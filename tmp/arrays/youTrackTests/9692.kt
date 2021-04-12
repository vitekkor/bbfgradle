// Original bug: KT-7037

class A {
    companion object {
        val B = 1
    }
    object B {
        val prop = 1
    }

    fun foo() {
        val x1: Any = B // resolved to object
        val x2: Int = B.prop // OK
    }
    val x1: Any= A.B // also resolved to object
    val x2: Int = A.B.prop // OK
}
