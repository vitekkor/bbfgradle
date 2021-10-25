// Original bug: KT-32450

fun main() {
    println(foo(Result.runCatching { 1 }) + 1)
}

fun <T> foo(a: Result<T>): T = bar(a) { 
    println(it)
    it.getOrThrow()
}

fun <T, R> bar(value: T, f: (T) -> R): R {
    return f(value)
}
