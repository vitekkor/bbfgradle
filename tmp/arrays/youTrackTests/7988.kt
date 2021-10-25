// Original bug: KT-23900

import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    var file = File("removeMe").apply { createNewFile(); deleteOnExit() }
    var pop = Array(28, { DoubleArray(32) })
    val toRz = {
        synchronized(file) {
            FileReader(file.path).use { df ->
                for ((popIdx, paramSet) in pop.withIndex()) {
                    println("foo!")
                }
            }
        }
    }
}
