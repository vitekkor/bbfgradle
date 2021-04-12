// Original bug: KT-10806

open class Foo

fun Foo.bar() = "bar"

fun Foo.foo(bar: Foo.() -> String) {
    val a = bar()
    object : Foo() {
        override fun toString() = bar() //resolved to parameter bar
    }
}
