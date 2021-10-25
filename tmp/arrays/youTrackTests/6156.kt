// Original bug: KT-31239

sealed class SealedClass {
    data class ClassOne(val data: Int) : SealedClass()
    data class ClassTwo(val value: String) : SealedClass()

    override fun equals(other: Any?): Boolean =
        other is SealedClass &&
            when (other) {
                is ClassOne -> (this as ClassOne) == other
                is ClassTwo -> (this as ClassTwo) == other
            }

    override fun hashCode(): Int =
        // DO NOT REMOVE the as? cast as it will turn them into recursive hashCode calls
        when (this) {
            is ClassOne -> (this as? ClassOne).hashCode()
            is ClassTwo -> (this as? ClassTwo).hashCode()
        }
}
