// Original bug: KT-40008

package main

import java.util.*
import kotlin.system.measureNanoTime

class Foo {
    private var x = 0

    fun getX() = x
    fun setXExpected(x: Int): Foo { this.x = x; return this }
    fun setXApply(x: Int) = apply { this.x = x }
}

fun main() {
    val iterations = 1000000000
    profile(iterations, true)
    profile(iterations, false)
}

private fun profile(iterations: Int, isDryRun: Boolean) {

    val foo = Foo()
    val monkeyStart = Random(3049503453L).nextInt()

    var monkey = monkeyStart

    if (isDryRun) println("Warming up...")

    if (!isDryRun) print("Overhead 1...")
    val time3Ns = measureNanoTime { for (i in 0 until iterations) { monkey += i } }
    if (!isDryRun) println(" Avg Time (ns): " + time3Ns / iterations.toDouble() + ", monkey: $monkey, foo.x: " + foo.getX())

    monkey = monkeyStart

    if (!isDryRun) print("Overhead 2...")
    val time4Ns = measureNanoTime { for (i in 0 until iterations) { monkey += i } }
    if (!isDryRun) println(" Avg Time (ns): " + time4Ns / iterations.toDouble() + ", monkey: $monkey, foo.x: " + foo.getX())

    monkey = monkeyStart

    if (!isDryRun) print("Expected...  ")
    val timeANs = measureNanoTime { for (i in 0 until iterations) { foo.setXExpected(monkey); monkey += i } }
    if (!isDryRun) println(" Avg Time (ns): " + timeANs / iterations.toDouble() + ", monkey: $monkey, foo.x: " + foo.getX())

    monkey = monkeyStart

    if (!isDryRun) print("Apply...     ")
    val timeENs = measureNanoTime { for (i in 0 until iterations) { foo.setXApply(monkey); monkey += i } }
    if (!isDryRun) println(" Avg Time (ns): " + timeENs / iterations.toDouble() + ", monkey: $monkey, foo.x: " + foo.getX())
}
