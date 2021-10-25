// Original bug: KT-33368

class Class {
    fun foo(block: suspend (v1: Int) -> Any?) {}
    fun foo(block: suspend (v1: Int, v2: String) -> Any?) {}

    suspend fun bar(v1: Int): Any? = null

    fun run() {
        foo(this::bar)
    }
}
