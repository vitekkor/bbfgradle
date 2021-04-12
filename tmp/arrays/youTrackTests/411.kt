// Original bug: KT-40976

suspend fun invoke0(block: Any) = kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn<Any?> {
    (block as Function1<Any?, Any?>).invoke(it)
}
