// Original bug: KT-10711

class Case<T>
fun <T> test(case: Case<T>) {}
fun runTest(method: (Case<Any>) -> Unit) {}

fun main(args: Array<String>) {
    runTest(::test) // type inference failed
}
