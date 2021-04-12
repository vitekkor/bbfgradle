// Original bug: KT-38287

class Dummy<T>(private val value: T) {
    fun <R> runValue(block: T.() -> R): R = block(value)
}

fun <V> Dummy<Result<V>>.resultValue(): V = runValue {
    getOrThrow()
}

fun main() {
    val value: String = Dummy(Result.success("Hello")).resultValue()
    println(value)
}
