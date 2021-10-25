// Original bug: KT-40843

import kotlin.coroutines.*

fun main() {
    builder {
        signInFlowStepFirst()
    }
    continuation!!.resumeWithException(Exception("BOOYA"))
}

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Unit>) {
            result.getOrThrow()
        }
    })
}

var continuation: Continuation<Unit>? = null

suspend fun suspendMe() = suspendCoroutine<Unit> { continuation = it }

@Suppress("RESULT_CLASS_IN_RETURN_TYPE")
suspend fun signInFlowStepFirst(): Result<Unit> = try {
    Result.success(suspendMe())
} catch (e: Exception) {
    Result.failure(e)
}
