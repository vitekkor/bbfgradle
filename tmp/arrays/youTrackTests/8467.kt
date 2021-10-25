// Original bug: KT-20451

interface I {
    fun foo(x: Int = 23)
}

interface K : I

abstract class Base : K

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
