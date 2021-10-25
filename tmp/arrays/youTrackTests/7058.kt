// Original bug: KT-29040

import kotlin.coroutines.*

fun main() {
    suspend fun Rec.dfs() {}
    Rec { dfs() }
}

@RestrictsSuspension
class Rec(block: suspend Rec.() -> Unit) {
    init { block.startCoroutine(this, Continuation(EmptyCoroutineContext) {}) }
}
