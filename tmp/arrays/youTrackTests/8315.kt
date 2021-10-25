// Original bug: KT-11215

/**
 * This method is an indexed partition, which works like [List.partition] but passes an index of the current element to the predicate.
 */
fun <T : Any> List<T>.partitionIndexed(predicate: ((Int, T) -> Boolean)): Pair<List<T>, List<T>> {
    val first = ArrayList<T>()
    val second = ArrayList<T>()
    for ((index, element) in this.withIndex()) {
        if (predicate(index, element)) {
            first.add(element)
        } else {
            second.add(element)
        }
    }
    return Pair(first, second)
}
