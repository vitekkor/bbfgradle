// Original bug: KT-10210

class Outer<T : Any> {
    private val any: Any = ""
    private val map: MutableMap<String, T> = hashMapOf<String, T>()

    private fun thing() {
        @Suppress("UNCHECKED_CAST")
        map["foo"] = any as T // does not suppress
    }
}

