// Original bug: KT-9906

interface A {
    fun foo(x: Int)
}
open class C {
    open fun foo(x: Int) {
        println("C")
    }
}

class B : C(),A {
    @JvmOverloads
    fun foo(x: Int, y: Int = 42) {
        println("B")
    }
}

fun main(args: Array<String>) {
    val a : A = B()
    (B() as A).foo(1)
    (B() as C).foo(1)
}
