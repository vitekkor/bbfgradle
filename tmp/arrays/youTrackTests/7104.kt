// Original bug: KT-28004

fun main() {
    val results = listOf(
        "value1" to runCatching { "Hi" },
        "value2" to runCatching { throw Exception("Oops") },
        "value3" to runCatching { throw Exception("Oops 2") }
    ).toMap()
    
    val (ok, errors) = results.partitionBySuccess()
    
    println("Ok values were: ${ok.keys.joinToString()}")
    println("Failed values were: ${errors.keys.joinToString()} ... where the first error was: ${errors.values.first()}")
}

inline fun <K, V> Map<K, Result<V>>.partitionBySuccess() = 
    filter { it.value.isSuccess }.map { it.key to it.value.getOrThrow() }.toMap() to filter { it.value.isFailure }.map { it.key to it.value.exceptionOrNull() }.toMap()
