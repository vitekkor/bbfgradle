// Original bug: KT-20711

abstract class Base(val x: Int)

interface IFoo {
    fun foo()
}

object FooImpl : IFoo {
    override fun foo() {
        println("FooImpl")
    }
}

class DerivedWithoutPrimaryCtor : Base, IFoo {
    private val `delegate$0` = FooImpl

    override fun foo() {
        `delegate$0`.foo()
    }

    constructor(x: Int) : super(x)
}
