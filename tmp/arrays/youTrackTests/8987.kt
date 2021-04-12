// Original bug: KT-16813

interface IFoo {
    fun foo()
}

interface IBar

class C {
    private fun createAnonObject() = // OK
            object : IFoo, IBar {
                override fun foo() {}
                fun qux() {}
            }

    fun useAnonObject() {
        createAnonObject().foo() // OK
        createAnonObject().qux() // OK
    }
}
