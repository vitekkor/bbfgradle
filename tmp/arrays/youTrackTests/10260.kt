// Original bug: KT-3822

class B() : Function0<Unit> {
    override fun invoke() {}

    fun foo() {
        this() // Exception
    }

    // OK
    fun bar() {
        val t = this
        t()
        t.invoke()
    }
}
