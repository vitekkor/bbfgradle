// Original bug: KT-29492

import kotlin.coroutines.*

interface Flow<T> {
    suspend fun consumeEach(consumer: FlowConsumer<T>)
}

interface FlowConsumer<T> {
    suspend fun consume(value: T)
}

// This functions cross-inlines action into an implementation of FlowConsumer interface
suspend inline fun <T> Flow<T>.consumeEach(crossinline action: suspend (T) -> Unit) =
    consumeEach(object : FlowConsumer<T> {
        override suspend fun consume(value: T) = action(value)
    })
