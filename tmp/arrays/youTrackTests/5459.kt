// Original bug: KT-30853

import kotlin.experimental.ExperimentalTypeInference

suspend fun main() {
    iFlow { emit(1) }.collect { println(4) }
}


@UseExperimental(ExperimentalTypeInference::class)
fun <T> iFlow(@BuilderInference block: suspend iFlowCollector<in T>.() -> Unit): iFlow<T> = TODO()

suspend fun <T> iFlow<T>.collect(action: suspend (T) -> Unit) {}

interface iFlowCollector<T> {
    suspend fun emit(value: T)
}
interface iFlow<out T>
