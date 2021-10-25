// Original bug: KT-28507

import kotlin.coroutines.intrinsics.*

suspend inline fun foo() = suspendCoroutineUninterceptedOrReturn<String> {
    error("Failed")
}

// Extra     INVOKESTATIC kotlin/jvm/internal/InlineMarker.mark (I)V invocation in here
suspend fun bar() {
    foo() 
}
