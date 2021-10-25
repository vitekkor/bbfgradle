// Original bug: KT-11557

open class Base {
    open fun foo() {
        println("Base::foo")
    }
}

interface IFoo {
    fun foo()
}

object FooImpl : IFoo {
    override fun foo() {
        println("FooImpl::foo")
    }
}

class Derived : Base(), IFoo by FooImpl {
    // delegate to FooImpl::foo() overrides unrelated Base::foo() 
}

fun main(args: Array<String>) {
    Derived().foo() // FooImpl::foo
}
