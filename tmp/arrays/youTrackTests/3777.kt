// Original bug: KT-38078

abstract class A {
    abstract override fun hashCode(): Int
}

interface I

class B : A(), I { // I is necessary here
    override fun hashCode() = super.hashCode()
}

fun main() {
    println(B().hashCode())
}
