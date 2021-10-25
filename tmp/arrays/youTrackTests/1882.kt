// Original bug: KT-17345

/*
 * Execution fails due to a type error on trying to get the
 * prototype of undefined. It turns out, it tries to access
 * "Kotlin.kotlin.Number" (In my own gradle-based build, exact path
 * may vary), which is never actually defined.
 */

fun main(args: Array<String>) {
    // This should just print "2"
    println(CustomInteger(2))
}

// Class that extends "kotlin.Number"
class CustomInteger(private var value: Int = 0) : Number() {
    override fun toString() = value.toString()
    override fun toByte() = value.toByte()
    override fun toShort() = value.toShort()
    override fun toInt() = value
    override fun toLong() = value.toLong()
    override fun toFloat() = value.toFloat()
    override fun toDouble() = value.toDouble()
    override fun toChar() = value.toChar()
}
