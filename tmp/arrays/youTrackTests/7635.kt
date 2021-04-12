// Original bug: KT-11026

inline fun <reified T> arrayEachSlice(array: Array<T>, size: Int): Array<Array<T>?> {
    val result: Array<Array<T>?> = arrayOfNulls(array.size.div(size) + 1)
    for (i in 0..array.lastIndex step size) {
        result[i.div(size)] = if (i == array.lastIndex) {
            arrayOf(array.last())
        } else
            array.copyOfRange(i, if (i + size <= array.lastIndex + 1) i + size else array.lastIndex + 1)
    }
    return result
}

