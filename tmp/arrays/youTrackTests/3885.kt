// Original bug: KT-37502

object A {
    inline fun <reified T> foo(noinline consumer: (T) -> Int) {
        consumer.hashCode()  // do something with the consumer
    }
}

fun test() {
    A.foo(consumer = { _: Throwable -> 42 })  // warning: "redundant lambda arrow"
    A.foo { _: Throwable -> 24 }  // warning: "redundant lambda arrow"
}
