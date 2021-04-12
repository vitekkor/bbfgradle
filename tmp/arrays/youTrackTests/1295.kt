// Original bug: KT-27427

interface A {
    fun foo()
}

class B : A {
    override fun foo() {
    }
}

fun test1() {
    val b = B()
    (b as A).foo() // Null-check is absent
}

fun test2() {
    val b = getB()
    (b as A).foo() // Null-check is generated
}

fun test3() {
    val b = getB()
    b.foo() // Null-check is absent
}

fun getB(): B = B()
