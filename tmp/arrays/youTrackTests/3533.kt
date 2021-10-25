// Original bug: KT-7657

public inline fun <T, R> Iterable<T>.accumulate(initial: R, operation: (previous: R, T) -> R): List<R> {
    var accumulator = arrayListOf(initial)
    for (element in this) accumulator.add(operation(accumulator.last(), element))
    return accumulator
}
