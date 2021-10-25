// Original bug: KT-23730

package co

interface Base {
    fun action(consumer: (Base) -> Unit) {
        consumer(this)
    }
}

interface Foo : Base {
    val t: String

    fun other(): String {
        return "Foo"
    }
}

interface FooEx : Foo {
    override fun other(): String {
        return "Bar"
    }
}


class FooImpl(override val t: String) : Foo
class FooExImpl(val delegate: Foo): FooEx, Foo by delegate {

    // THIS IS REPORTED BY DIAGNOSTIC `DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE`
    override fun other(): String {
        return super<FooEx>.other()
    }
    
    // THIS IS NOT REPORTED, if removed code breaks
    // override fun action(consumer: (Base) -> Unit) {
    //     super<FooEx>.action(consumer)
    //}
}

fun main(args: Array<String>) {
    val foo = FooImpl("Foo")
    val fooEx = FooExImpl(foo)
    // See override in FooExImpl.action, if it present all works as expected, if not:
    // Expected that fooEx.action actually calls (fooEx -> FooEx -> Foo -> Base).action
    // Actually fooEx calls (fooEx.delgate -> Foo -> Base).action
    fooEx.action { check(it === fooEx) }
}
