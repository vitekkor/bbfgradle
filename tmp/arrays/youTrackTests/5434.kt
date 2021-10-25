// Original bug: KT-30070

package flow

interface FlowCollector<T> {
    suspend fun emit(value: T)
}

interface Flow<T : Any> {
    suspend fun collect(collector: FlowCollector<T>)
}

abstract class FuseableFlow<T : Any, R : Any>() : Flow<R> {}

typealias Transformer<T, R> = suspend (T) -> R?

public inline fun <T : Any> flow(crossinline block: suspend FlowCollector<T>.() -> Unit) = object : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) = collector.block()
}

suspend inline fun <T : Any> Flow<T>.collect(crossinline action: suspend (T) -> Unit): Unit =
    collect(object : FlowCollector<T> {
        override suspend fun emit(value: T) = action(value)
    })

public suspend fun <T: Any> Flow<T>.awaitSingle(): T {
    var result: T? = null
    collect {
        if (result != null) error("Expected only one element")
        result = it
    }

    return result ?: throw NoSuchElementException("Expected at least one element")
}
