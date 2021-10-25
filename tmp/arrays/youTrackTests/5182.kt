// Original bug: KT-34320

open class Base {
    open var foo: Int = 1
}

class Foo : Base() {
    override var foo: Int
        get() = super.foo
        set(value) {
            super.foo = value
        }
}

