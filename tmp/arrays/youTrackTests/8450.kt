// Original bug: KT-20737

import kotlin.reflect.*

object Ddd {
    operator fun getValue(thisRef: Any?, p: KProperty<*>): Any {
        println("get")
        return 1
    }
}

val b by Ddd

fun main(args: Array<String>) {
    val a by Ddd
    println(a)
    println(a)
    println(b)
    println(b)
}
