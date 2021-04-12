// Original bug: KT-18277

import java.util.concurrent.*
import kotlin.concurrent.*
import kotlin.reflect.full.*

class A(val bar: String)

fun main(args: Array<String>) {
    val latch = CountDownLatch(1)
    val threads = (1..100).map {
        thread(start = false) {
            latch.await()
            A::class.primaryConstructor
        }
    }
    for (thread in threads) {
        thread.start()
    }
    latch.countDown()
    for (thread in threads) {
        thread.join()
    }
}
