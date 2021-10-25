// Original bug: KT-40247

class Foo {
    suspend operator fun <T> invoke(body: () -> T) = null as T
}

suspend fun main() {
    val retry = Foo()
    try {
        retry { 1 } // Suspend function 'invoke' should be called only from a coroutine or another suspend function
    } catch (e: Exception) { }
}
