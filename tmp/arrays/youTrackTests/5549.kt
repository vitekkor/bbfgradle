// Original bug: KT-23049

fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this) // apply Replace with 'map{}' intention on `for`
        result.add(transform(item))
    return result
}
