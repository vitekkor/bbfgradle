// Original bug: KT-27927

inline fun <reified T : Any> Array<Array<T>>.alternateRowsPairwise(): Array<Array<T>> {
    return foldIndexed(arrayListOf<Pair<Array<T>, Array<T>?>>()) { index, acc, row ->
        acc.apply {
            if (index % 2 == 0)
                add(row to null)
            else {
                add(last().copy(second = row))
                remove(acc.last())
            }
        }
    }.flatMap { pair -> arrayListOf(pair.second, pair.first) }
            .filterNotNull()
            .toTypedArray()
}
