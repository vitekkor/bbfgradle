// Original bug: KT-40009

open class Bar {
    protected open fun bar() {
        println("Bar")
    }
}

open class Foo : Bar() {

    override fun bar() {
        println("Foo")
    }

    fun foo() {
        object : Bar() {
        }.apply { // I expect inspection to warn me that `this` in this `apply` is unused
            bar() // Equivalent to this@Foo.getName()
        }
    }
}

fun main() {
    Foo().foo()
}
