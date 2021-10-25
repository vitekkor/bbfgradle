// Original bug: KT-39413

sealed class Boo(val ordinal: Int) : Comparable<Boo> {
    val name: String = javaClass.simpleName
    final override fun compareTo(other: Boo) = compareValues(ordinal, other.ordinal)

    sealed class BooWithFoo(
            ordinal: Int,
            val foo: String
    ) : Boo(ordinal) {
        object BAZ : Boo(0)
        object BAM : BooWithFoo(1, "bar")
    }

    companion object {
        fun values() = listOf(Boo::class, BooWithFoo::class)
                .flatMap { it.sealedSubclasses }
                .mapNotNull { it.objectInstance }
    }
}
