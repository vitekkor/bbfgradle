// Original bug: KT-3114

fun <T, R: Comparable<R>>Collection<T>.max(map: (T) -> R) : T {
    var answer = this.first()
    this.forEach {
        if (map(answer) < map(it)) answer = it;
    }
    return answer;
}
