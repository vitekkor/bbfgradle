// Original bug: KT-30657

import kotlin.experimental.ExperimentalTypeInference

interface ProducerScope<E> {
    fun yield(e: E)
}

@UseExperimental(ExperimentalTypeInference::class)
fun <E> produce(@BuilderInference block: ProducerScope<E>.() -> Unit): ProducerScope<E> = TODO()

fun <K> filter(e: K, predicate: (K) -> Boolean) =
    produce {
        predicate(e) // debug info unresolved reference
        yield(42)
    }
