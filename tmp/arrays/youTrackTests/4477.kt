// Original bug: KT-21178

open class Foo {
    protected fun protectedMethod() {}

    inline fun inlineFun() {
        `access$protectedMethod`() // OK, no warning or error
    }

    @PublishedApi
    internal fun `access$protectedMethod`() = protectedMethod()
}
