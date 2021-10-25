// Original bug: KT-28879

package sample

import java.util.concurrent.atomic.AtomicReference

fun main(args: Array<String>) {
    println("A: " + UByte::class)
    println("B: " + 0.toUByte().javaClass)
    println("C: " + 0.toUByte()::class)
    println("D: " + AtomicReference(0.toUByte()).get()::class)
    println("E: " + AtomicReference(0.toUByte()).get().javaClass)
}
