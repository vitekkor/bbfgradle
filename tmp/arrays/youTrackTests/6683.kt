// Original bug: KT-15813

open class Foo {
    @Deprecated("")
    open fun bar() {}
}

class Bar : Foo() {
    override fun bar() {}
}

fun test(foo: Foo, bar: Bar) {
    foo.bar() // warning, ok
    
    bar.bar() // warning, not ok!
    if (foo is Bar) {
        foo.bar() // warning, not ok!
    }
}
