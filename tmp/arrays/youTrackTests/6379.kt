// Original bug: KT-30073

package flow

interface FlowCollector<T> {
    suspend fun emit(value: T)
}

interface Flow<T : Any> {
    suspend fun collect(collector: FlowCollector<T>)
}

public inline fun <T : Any> flow(crossinline block: suspend FlowCollector<T>.() -> Unit) = object : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) = collector.block()
}

suspend inline fun <T : Any> Flow<T>.collect(crossinline action: suspend (T) -> Unit): Unit =
    collect(object : FlowCollector<T> {
        override suspend fun emit(value: T) = action(value)
    })

inline fun <T : Any, R : Any> Flow<T>.flowWith(crossinline builder: Flow<T>.() -> Flow<R>): Flow<T> =
    flow {
        builder(this@flowWith)
    }

suspend fun main() {
    val f = flow<Int> {
        emit(1)
    }.flowWith {
        this
    }.collect {
    }
}
