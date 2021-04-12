// Original bug: KT-38992


class Outer<E> {
    inner class Inner

    fun foo(x: (Inner) -> Unit, y: Inner.() -> Unit) {
        // each call reported as INAPPLICABLE because "Inner<E> is not a subtype of Inner"
        bar(Inner()) 
        x(Inner())
        Inner().y()
    }

    fun bar(i: Inner) {}
}
