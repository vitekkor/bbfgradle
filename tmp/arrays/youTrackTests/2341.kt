// Original bug: KT-37279

package pkg

import kotlin.coroutines.*

inline suspend fun f(block: () -> Int): Int {
    var result = 0
    
    try {
        result = block()
        return result
    } finally {
        complete(result)
    }
}

suspend fun complete(v: Int) {
}

fun main(args: Array<String>) {
    val s = suspend {
        println(f {
            777
        })
    }
    
    s.startCoroutine(object : Continuation<Unit> {
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Unit>) {
                println("Result is $result")
            }
        })
}
