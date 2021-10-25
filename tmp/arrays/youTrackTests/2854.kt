// Original bug: KT-40167

data class H(val op: String = "empty", val freq: Map<String, Int> = emptyMap<String, Int>().withDefault{ 0 } ): Map<String, Int> by freq {
    fun getValue(key: String): Int = freq.getValue(key)
}
