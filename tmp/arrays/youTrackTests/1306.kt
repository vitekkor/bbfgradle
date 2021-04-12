// Original bug: KT-44481

class A {
    suspend fun foo() {}

    suspend fun test() {
        b(::foo) // Note: callable reference
    }
}

suspend inline fun b(block: suspend () -> Unit) {}
