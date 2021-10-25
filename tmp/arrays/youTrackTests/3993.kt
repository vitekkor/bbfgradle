// Original bug: KT-27440

fun <T> executeAsync(producerConsumer: () -> Pair<T, (T) -> Unit>) {}

fun test() {
    executeAsync {
        Pair(42, { result -> println("it's $result") }) // Error in OI, OK in NI
    }
}
