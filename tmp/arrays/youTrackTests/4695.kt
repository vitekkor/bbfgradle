// Original bug: KT-35396

class Element<K, out V>

class SSet<out E>(val root: Element<@UnsafeVariance E, Unit>?) {
    private fun merge(other: SSet<E>) {
        other.root
    }
}
