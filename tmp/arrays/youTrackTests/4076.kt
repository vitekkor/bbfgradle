// Original bug: KT-28614

data class AB(val a: Any, val b: Any) {
    init {
        if (a !is ByteArray || a !is String || a.javaClass != b.javaClass)
            throw Exception()
    }
}
