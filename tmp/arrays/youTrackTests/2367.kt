// Original bug: KT-41960



interface A {
    fun foo(): String
}

class AA : A {
    override fun foo(): String = "AA"
}

class C(a: A) : A by a

fun main() {
    val c = C(AA())
    //Breakpoint!
    c.foo()
}
