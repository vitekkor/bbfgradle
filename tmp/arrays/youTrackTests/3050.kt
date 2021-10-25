// Original bug: KT-39824

fun main() {
    diContext("Hello").print()
    diContext { 1 }.print()
}

fun < C : Any> diContext(context: C): DIContext<C> = DIContext<C>(TypeToken<C>(), context)
fun < C : Any> diContext(getContext: () -> C) : DIContext<C> = DIContext<C>(TypeToken<C>()) { getContext() }

interface DIContext<C : Any> {
    val type: TypeToken<C>
    val value: C

    fun print() {
        println("$type -> $value")
    }

    data class Value<C : Any>(override val type: TypeToken<C>, override val value: C) : DIContext<C>
    class Lazy<C : Any>(override val type: TypeToken<C>, public val getValue: () -> C) : DIContext<C> {
        override val value: C by lazy(getValue)
    }

    companion object {
        operator fun <C : Any> invoke(type: TypeToken<C>, value: C): DIContext<C> = Value(type, value)
        operator fun <C : Any> invoke(type: TypeToken<C>, getValue: () -> C): DIContext<C> = Lazy(type, getValue)
    }
}

class TypeToken<T>
