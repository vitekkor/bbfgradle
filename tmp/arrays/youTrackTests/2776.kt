// Original bug: KT-40010

open class Bar {
    protected fun getName(): String {
        TODO()
    }
}

open class Foo : Bar() {

    fun foo() {
        object : Bar() {
        }.apply {
            getName() // Equivalent to this@Foo.getName()
        }

        object : Foo() {
        }.apply {
            getName() // Equivalent to this@apply.getName()
        }
    }
}
