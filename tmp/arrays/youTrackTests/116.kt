// Original bug: KT-43130

fun main() {
    foo(Result.runCatching { 1 })
}

interface I<T, R> {
    fun test(t: T): R
}

fun <T> foo(a: Result<T>): T = bar(a, object : I<Result<T>, T> {
    override fun test(t: Result<T>): T {
        println(t)
        return t.getOrThrow()
    }
})

fun <T, R> bar(value: T, f: I<T, R>): R {
    return f.test(value)
}
