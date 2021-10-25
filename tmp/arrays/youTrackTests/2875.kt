// Original bug: KT-40060

import kotlin.experimental.ExperimentalTypeInference

class TypeDefinition<K : Any> {
    fun parse(parser: (serializedValue: String) -> K?): Unit {}
    fun serialize(parser: (value: K) -> Any?): Unit {}
}

@OptIn(ExperimentalTypeInference::class)
fun <T : Any> defineType(@BuilderInference definition: TypeDefinition<T>.() -> Unit): Unit {}

fun test() {
    defineType {
        parse { it.toInt() }
        serialize { it.toString() }
    }
}

fun main() {
    test()
}
