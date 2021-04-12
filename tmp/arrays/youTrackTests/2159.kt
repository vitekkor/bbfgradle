// Original bug: KT-41962



import kotlin.coroutines.*

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(object : Continuation<Any?> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(result: Result<Any?>) { result.getOrThrow() }
    })
}

var cnt: Continuation<Unit>? = null

fun main() {
    builder {
        inFun()
    }
    cnt!!.resume(Unit)
}

suspend fun inFun() {
    //Breakpoint!
    run()
}

suspend fun run() {
    suspendCoroutine { c: Continuation<Unit> ->
        cnt = c
    }
}
