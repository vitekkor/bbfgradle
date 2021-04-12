// Original bug: KT-33739

class `Parentheses & brackets indentation is inconsistent`(
    private val b1: Boolean,
    private val b2: Boolean,
    private val b3: Boolean
) {

    fun `Parameters look nice`(
        p1: String,
        p2: String
    ) {
        print(p1 + p2)
    }

    fun `Expressions are ugly (function)`() = (
        b1 &&
            (b2 || b3) // Possibly too much indentation
        ) // Definitely too much indentation

    val `Expressions are ugly (property)`
        get() = (
            b1 &&
                (b2 || b3) // Possibly too much indentation
            ) // Definitely too much indentation

    annotation class A1
    annotation class A2

    @field:[
    A1 // Too little indentation
    A2 //
    ]
    val annotated = null
}
