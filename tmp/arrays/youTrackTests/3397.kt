// Original bug: KT-38870

interface I {
    val v1: Int

    fun print() {
        println("v1 = $v1")
    }
}

class A : I {
    override val v1: Int
        get() = 1
}

class C(original: I) : I by original {
    override var v1: Int = original.v1
}

fun main() {
    val a = A()
    val c = C(a)
    c.v1 = 2
    c.print()
}
