// Original bug: KT-38874

package test

interface IFooA {
    fun foo(a: String = "X"): String
}

interface IFooB {
    fun foo(a: String): String
}

class AB : IFooA, IFooB {
    override fun foo(a: String): String = a
}

class BA : IFooB, IFooA {
    override fun foo(a: String): String = a
}

fun main() {
    println(AB().foo()) // > X
    println(BA().foo()) // java.lang.NoClassDefFoundError: test/IFooB$DefaultImpls
}
