// Original bug: KT-27618

sealed class Aa()
class C : Aa() {
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
