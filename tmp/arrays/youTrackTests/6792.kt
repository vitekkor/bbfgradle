// Original bug: KT-7529

interface Dictionary<K, V>
interface StringMultiDictionary : Dictionary<String, Array<String>>

fun <T> foo(x: Dictionary<T, T>) {
    if (x is StringMultiDictionary) {
        // dead code
    }
}
