// Original bug: KT-42554

import kotlin.coroutines.*

fun launch(block: suspend () -> String): String {
    var result = ""
    block.startCoroutine(Continuation(EmptyCoroutineContext) { result = it.getOrThrow() })
    return result
}

enum class E { A }

class C {
    val result = launch {
        when (E.A) {
            E.A -> "OK"
        }
    }
}

fun box(): String = C().result
