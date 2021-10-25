// Original bug: KT-43361

import kotlin.coroutines.*

suspend fun main() {
    class DummyDeferred(val value: Int) {
        suspend fun await(): Int = suspendCoroutine { c -> c.resume(value) }
    }
    val v1 = DummyDeferred(10)
    val varray = arrayOf<Any>(v1.await())
    println(varray[0]) // 10 on Legacy, undefined on IR
}
