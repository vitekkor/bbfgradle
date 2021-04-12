// Original bug: KT-30761

import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.test.assertTrue

fun interceptor(): ContinuationInterceptor =  TODO() // Note: type ContinuationInterceptor that is CoroutineContext

suspend fun foo() {
    val interceptor: CoroutineContext = interceptor() // Note: type is CoroutineContext
    val context = coroutineContext
    assertTrue(context[ContinuationInterceptor] === interceptor)
}
