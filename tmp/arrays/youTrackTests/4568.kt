// Original bug: KT-35684

import kotlin.experimental.ExperimentalTypeInference

fun main() {
    sequence {
        yield(materialize<Int>())
        yield(materialize())
    }
}

@UseExperimental(ExperimentalTypeInference::class)
fun <T> sequence(@BuilderInference block: suspend Inv<T>.() -> Unit) {
}

interface Inv<T> {
    fun yield(element: T)
}

fun <K> materialize(): Inv<K> = TODO()
