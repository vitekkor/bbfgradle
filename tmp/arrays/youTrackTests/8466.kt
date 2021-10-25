// Original bug: KT-20451

interface I {
    fun foo(x: Int = 23)
}

abstract class Base : I

class C : Base() {
    override fun foo(x: Int) {
        println("C:$x")
    }
}

fun bar(x: I) {
    x.foo()
    x.foo(42)
}

fun main(args: Array<String>) {
    bar(C())
}
