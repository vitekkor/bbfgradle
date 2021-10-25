// Original bug: KT-43130

fun main() {
    foo(Result.runCatching { 1 })
}

fun <T> foo(a: Result<T>): T = bar(a) {
    println(it)
    it.getOrThrow()
}

fun <T, R> bar(value: T, f: (T) -> R): R {
    return f(value)
}
