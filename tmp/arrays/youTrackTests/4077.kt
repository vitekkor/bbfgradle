// Original bug: KT-28614

data class AB(val a: Any, val b: Any) {
    init {
        if (a.javaClass != b.javaClass || a !is ByteArray || a !is String)
            throw Exception()
    }
}
