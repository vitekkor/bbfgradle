// Original bug: KT-33739

class Foo(
    private val b1: Boolean,
    private val b2: Boolean,
    private val b3: Boolean
) {
    fun `Expressions are ugly (function)`() = (
            b1 &&
                    (b2 || b3) // Possibly too much indentation
            ) // Definitely too much indentation

}
