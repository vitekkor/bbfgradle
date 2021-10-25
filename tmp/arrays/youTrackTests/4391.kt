// Original bug: KT-33693

val map = mutableMapOf<String, String>()

inline fun <reified T> get(key: String): T? =
    map[key]?.let {
        when (T::class) {
            String::class -> it as T
            Int::class -> it.toInt() as T
            Float::class -> it.toFloat() as T
            Double::class -> it.toDouble() as T
            else -> throw IllegalArgumentException("Not implemented for the given type.")
        }
    }

fun test() {
    map["count"] = "1"

    assert(getAsInt("count") == 1)
    assert(getAsIntOptimal("count") == 1)
}

//inlined will all when branches, ~50 lines of bytecode
private fun getAsInt(key: String) = get<Int>(key)

//ideal result, ~10 lines of bytecode
private fun getAsIntOptimal(key: String) = map[key]?.toInt()
