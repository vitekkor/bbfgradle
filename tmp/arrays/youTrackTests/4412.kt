// Original bug: KT-33544

import kotlin.experimental.ExperimentalTypeInference

class G<E> {
    fun send(e: E) {}
}

@UseExperimental(ExperimentalTypeInference::class)
fun <T> builder(@BuilderInference block: G<T>.() -> Unit): Unit = TODO()

fun test() {
    builder {
        send(run {
            let { 0 } ?: 1
            0
        })
    }
}
