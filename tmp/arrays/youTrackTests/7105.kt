// Original bug: KT-28004

fun main() {
    val (ok, errors) = listOf(
        "value1" to runCatching { "Hi" },
        "value2" to runCatching { throw Exception("Oops") },
        "value3" to runCatching { throw Exception("Oops 2") }
    ).partitionBySuccess()

    println("Ok inputs were: ${ok.keys.joinToString()}")
    println("Or more specifically:")
    ok.forEach { input, result -> println("$input yielded $result")  }

    if (errors.hasErrors()) {
        println("Failed inputs were: ${errors.all.keys.joinToString()} ... where the first error was: ${errors.first}")

        println("Or more specifically:")
        errors.all.forEach { input, error -> println("$input failed on ${error.message}") }
    }
}

inline fun <K, V> List<Pair<K, Result<V>>>.partitionBySuccess(): Pair<Map<K,V>, ErrorResults<K>> =
    filter { it.second.isSuccess }.toMap().mapValues { it.value.getOrThrow() } to ErrorResults(toMap())

inline class ErrorResults<K>(val map: Map<K, Result<*>>){
    val all: Map<K, Throwable>
        get() = map.filter { it.value.isFailure }.mapValues { it.value.exceptionOrNull() ?: Exception("Exception was null!") }

    val first: Throwable
        get() = map.entries.first { it.value.isFailure }.value.exceptionOrNull() ?: Exception("Exception was null!")

    fun hasErrors() = map.any { it.value.isFailure }
}
