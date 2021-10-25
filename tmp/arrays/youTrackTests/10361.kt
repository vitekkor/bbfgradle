// Original bug: KT-3153

fun <A, B> Iterable<A>.indexedMap(f: (Int, A) -> B): List<B> {
    val answer = ArrayList<B>()
    var nextIndex = 0
    for (e in this) {
        answer.add(f(nextIndex, e))
        nextIndex++
    }
    return answer
}
