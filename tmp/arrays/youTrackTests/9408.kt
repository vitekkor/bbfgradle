// Original bug: KT-6228

public fun <T> Iterable<T>.drop(n: Int): List<T> {
    var count = 0
    val list = ArrayList<T>()
    for (item in this) {
        if (count++ >= n) list.add(item)
    }
    return list
}
