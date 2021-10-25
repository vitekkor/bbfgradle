// Original bug: KT-42816

suspend fun bar() {}

class C {
    companion object {
        @JvmStatic
        suspend inline fun foo() {
            bar()
            bar()
        }
    }
}
