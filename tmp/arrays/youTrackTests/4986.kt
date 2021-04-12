// Original bug: KT-34915

interface Foo {

    val fooFoo: Runnable
    fun submitFooFoo()

    companion object {
        fun impl() = object : Foo {
            override val fooFoo = Runnable { TODO("Gotchu") }
            override fun submitFooFoo() {
                fooFoo.run()
            }
        }
    }

}

class Bar : Foo by Foo.impl() {
    override val fooFoo = Runnable { println("ok") }
    fun bar() = submitFooFoo()
}
