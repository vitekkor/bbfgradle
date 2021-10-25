// Original bug: KT-12152

interface IFoo {
    fun foo()
}

class FooImpl() : IFoo {
    override fun foo() = println(hashCode())
}

class Test1 : IFoo by FooImpl() {
    val x: String

    init {
        foo() // foo is a delegate member, not an own member: OK
        x = ""
    }

    override fun hashCode() = x.hashCode()
}

