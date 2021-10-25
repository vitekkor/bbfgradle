// Original bug: KT-31620

import kotlin.experimental.ExperimentalTypeInference

interface Inv<T> {
    fun send(e: T)
}

@UseExperimental(ExperimentalTypeInference::class)
fun <K> foo(@BuilderInference block: Inv<K>.() -> Unit) {}

fun test(i: Int) {
    // Error: not enough information
    foo {
        val p = send(i)
    }
}
