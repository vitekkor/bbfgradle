// Original bug: KT-42042

sealed class Subtype<A, B> {
    abstract fun cast(value: A): B

    class Trivial<A : B, B> : Subtype<A, B>() {
        override fun cast(value: A): B = value
    }
}

fun <A, B> unsafeCast(value: A): B {
    val proof: Subtype<A, B> = Subtype.Trivial()
    return proof.cast(value)
}
