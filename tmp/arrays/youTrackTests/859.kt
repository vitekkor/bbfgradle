// Original bug: KT-44646

inline fun <reified T> Any?.isInstanceOf() = this is T
inline fun <reified T> Any?.isArrayOf() = isInstanceOf<Array<T>>()

fun main() {
    arrayOf(1, 2, 3).isArrayOf<String?>()
}
