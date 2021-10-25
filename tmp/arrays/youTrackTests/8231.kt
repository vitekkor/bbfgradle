// Original bug: KT-22364

open class Foo {
    open var a = 42
}

class Boo(a: Int) : Foo() {
    override var a: Int = super.a
        get() = field // reported as redundant
        set(value) {
            field = value  // should be reported as redundant, but doesn't
        }
}
