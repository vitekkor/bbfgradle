// Original bug: KT-43361

import kotlin.coroutines.*

suspend fun main() {
    run {
        val v1 = DummyDeferred(10)
        val v2 = DummyDeferred(20)
        val varray = arrayOf<Any>(v1.await(), v2.await())
        println("-----------")
        println(varray.size == 2) // false. Should be true
        println(varray.size) // undefined. Should be 2
        println(varray[0]) // undefined. Should be 10
        println(varray[1]) // undefined. Should be 20
        println("-----------")
    }
    run {
        val v1 = DummyDeferred(10)
        val v2 = DummyDeferred(20)
        val r1 = v1.await()
        val r2 = v2.await()
        val varray = arrayOf(r1, r2)
        println("-----------")
        println(varray.size == 2) // true
        println(varray.size) // 2
        println(varray[0]) // 10
        println(varray[1]) // 20
        println("-----------")
    }
}

class DummyDeferred(val value: Int) {
    suspend fun await(): Int = suspendCoroutine { c -> c.resume(value) }
}
