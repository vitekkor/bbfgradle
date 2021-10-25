// Original bug: KT-33911

class Foo(
    private val b1: Boolean,
    private val b2: Boolean,
    private val b3: Boolean
) {
    val `Expressions are ugly (property)`
        get() = (
                b1 &&
                        (b2 || b3) // Possibly too much indentation
                ) // Definitely too much indentation
}
