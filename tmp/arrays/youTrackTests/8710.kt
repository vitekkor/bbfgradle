// Original bug: KT-19435

abstract class Base(val params: Array<Any> = emptyArray())

class Derived() : Base(arrayOf(1))

fun main(args: Array<String>) {
    println(Derived().params.contentToString()) // prints "[]"
}
