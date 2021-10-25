// Original bug: KT-34762

open class Foo {
    open fun str(): String {
        return "hello"
    }
}

class Bar : Foo() {
    override fun str(): String { // Find Usages to str()
        return super.str()
    }
}
