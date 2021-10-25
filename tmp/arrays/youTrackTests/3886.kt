// Original bug: KT-37502

inline fun <reified T> foo(noinline consumer: (T) -> Int) {
    consumer.hashCode()  // do something with the consumer
}

fun test() {
    foo(consumer = { _: Throwable -> 42 })  // no warning
    foo { _: Throwable -> 24 }  // no warning
}
