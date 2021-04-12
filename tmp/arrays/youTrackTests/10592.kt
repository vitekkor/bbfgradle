// Original bug: KT-3823

public inline fun <T: Comparable<T>> MutableIterable<T>.sort() : List<T> {
    val list = toCollection(ArrayList<T>())
    java.util.Collections.sort(list)
    return list
}
