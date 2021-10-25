// Original bug: KT-20231

interface Foo {
    fun test()
}

open class Bar : Foo {
    override fun test() { }
}

class Baz(val foo: Foo) : Bar(), Foo by foo {
    override fun test() = super.test() // False positive redundant override
}
