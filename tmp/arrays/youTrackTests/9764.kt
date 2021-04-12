// Original bug: KT-11305

public fun <T> Iterable<T>.take(n: Int): List<T> {
    require(n >= 0, { "Requested element count $n is less than zero." })
    if (n == 0) return emptyList()
    if (this is Collection<T> && n >= size) return toList()
    var count = 0
    val list = ArrayList<T>(n)
    for (item in this) {
        if (count++ == n)
            break
        list.add(item)
    }
    return list
}
