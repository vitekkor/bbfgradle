// Original bug: KT-10210

class Outer<T : Any> {
    lateinit var v: T

    inner class Inner {
        private fun thing(any: Any) {
            @Suppress("UNCHECKED_CAST")
            v = any as T
        }
    }
}
