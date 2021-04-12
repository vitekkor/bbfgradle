// Original bug: KT-15487

fun main(args: Array<String>) {
    fun foo(bar: (Any.() -> Unit)? = null) = Unit

    val bar: (Any.() -> Unit)? =
            if (true) ({}) else null

    foo(bar)

    foo(
            // Type mismatch: inferred type is () -> Unit but Nothing? was expected
            if (true) ({}) else null
    )
}
