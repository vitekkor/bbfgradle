// Original bug: KT-21029

object Namespace {
    fun enter(block: Namespace.() -> Unit) {
        block()           // <-- warning for unused expression
        Namespace.block() // <-- no warnings
        this.block()      // <-- no warnings
    }
}
