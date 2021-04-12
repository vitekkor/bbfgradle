// Original bug: KT-33238

abstract class Foo {
    protected abstract val qux: One
}

class Bar : Foo() {
    private val baz: Two = Two()
    override val qux by lazy { baz as One }
}

open class One
internal class Two : One()

