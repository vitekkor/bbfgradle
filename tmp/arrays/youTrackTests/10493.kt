// Original bug: KT-4998

public inline fun <T> Iterable<T>.first(predicate: (T) -> Boolean) : T {
    for (element in this) if (predicate(element)) return element
    throw IllegalArgumentException("No element matching predicate was found")
    
}
