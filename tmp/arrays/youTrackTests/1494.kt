// Original bug: KT-12710

package foo

class Bar {
    companion object {
        private val _map = hashMapOf(Pair("key", 1))

        fun getInt(key: String): Int = _map[key] ?: 0
    }
}
