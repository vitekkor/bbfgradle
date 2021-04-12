// Original bug: KT-10539

interface A {
    fun foo()
}

interface B {
    fun canCallFoo() = true
}

fun <T> T.doFoo() where T : A, T : B {
    if (canCallFoo()) {
        foo()
    }
}

class C : A, B {
    override fun foo() = println("Foo")
}

fun main(args: Array<String>) {
    val c = C()
    c.doFoo() // Does not work!
}
