// Original bug: KT-37048

abstract class A1<Q> : MutableCollection<Q> {
    override fun contains(o: Q): Boolean {
        throw UnsupportedOperationException()
    }

    override fun containsAll(c: Collection<Q>): Boolean {
        throw UnsupportedOperationException()
    }
}
