// Original bug: KT-26265

import java.util.concurrent.CompletableFuture

class Foo(private val cf: CompletableFuture<Boolean> = CompletableFuture()) {
    init {
        cf.completeExceptionally(throw Exception()) // No warning here
        bar()
    }

    private fun bar() {
        cf.completeExceptionally(throw Exception()) // UNREACHABLE CODE
    }
}
