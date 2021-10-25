// Original bug: KT-31784

@file:UseExperimental(ExperimentalTypeInference::class)
package test

import kotlin.experimental.ExperimentalTypeInference

interface Builder<T : Any> {
    fun filter(filter: (T) -> Boolean)
}

fun <T : Any> build(@BuilderInference block: Builder<T>.() -> Unit): T = TODO()

fun main() {
    build<Int> {
        filter {
            it > 10
        }
    }
}
