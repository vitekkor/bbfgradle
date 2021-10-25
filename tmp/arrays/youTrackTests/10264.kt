// Original bug: KT-5834


private class DistinctIterator<T, K>(val source : Iterator<T>, val keySelector : (T) -> K) : AbstractIterator<T>() {
    private val observed = HashSet<K>()

    override fun computeNext() {
        while (source.hasNext()) {
            val next = source.next()
            val key = keySelector(next)
            
            if (observed.add(key)) {
                setNext(next)
                return
            }
        }
        
        done()
    }
}

private class DistinctSequence<T, K>(val source : Sequence<T>, val keySelector : (T) -> K) : Sequence<T> {
    override fun iterator(): Iterator<T> = DistinctIterator(source.iterator(), keySelector)
}

public fun <T> Sequence<T>.distinct() : Sequence<T> = DistinctSequence(this) {it} 
public fun <T, K> Sequence<T>.distinct(keySelector : (T) -> K) : Sequence<T> = DistinctSequence(this, keySelector)

