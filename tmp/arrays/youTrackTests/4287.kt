// Original bug: KT-34982

fun main() {
    FooImpl().test()
}

open class Foo {
    open fun Int.bar() {
        println("Bar")
    }
}

class FooImpl : Foo() {
    override fun Int.bar() {
        with(this@FooImpl as Foo) { // this: Foo
            bar() // No 'Recursive call' gutter
        }
    }

    fun test() {
        5.bar()
    }
}

