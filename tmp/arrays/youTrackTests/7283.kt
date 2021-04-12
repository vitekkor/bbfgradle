// Original bug: KT-27560

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.full.memberProperties

abstract class AbstractTest {
    abstract val x: suspend (Unit) -> Unit
}

class Test: AbstractTest() {
    override val x = ::suspendX
    private suspend fun suspendX(unit: Unit): Unit = suspendCoroutine {
        it.resume(unit)
    }
}

fun main(args: Array<String>) {
    val test = Test()
    test::class.memberProperties.forEach {
        println(it.getter.call(test))
    }
}
