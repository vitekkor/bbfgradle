// Original bug: KT-43956

import kotlin.experimental.ExperimentalTypeInference

fun foo() = "OK"
fun bar() = "NOK"

interface FlowCollector<in T> {}

@UseExperimental(ExperimentalTypeInference::class)
fun <T> flow(@BuilderInference block: suspend FlowCollector<T>.() -> Unit) = Flow(block)

class Flow<out T>(private val block: suspend FlowCollector<T>.() -> Unit)

fun <R> select(vararg x: R) = x[0]

fun main(recursive: Boolean): Flow<String> {
    return flow {
        val inv = try { ::bar } finally { ::foo }
        inv()
    }
}
