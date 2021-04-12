// Original bug: KT-20024

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        for (x in 1..10000) {
            x.toString()
        }
    }
    println(elapsedTime)
}
