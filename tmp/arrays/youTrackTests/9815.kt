// Original bug: KT-10806

open class Foo
open class Bar

fun Bar.bar() = "bar"

fun Foo.foo(bar: Foo.() -> String) {
    val a = bar()
    object : Bar() {
        override fun toString() = bar() // resolved to extension
    }
}
