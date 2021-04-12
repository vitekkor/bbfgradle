// Original bug: KT-21758

import kotlin.reflect.KProperty
  
val delegated : Array<String> by object {
    private var holder: Array<String> = emptyArray()

    operator fun getValue(thisRef: Nothing?, property: KProperty<*>): Array<String> = holder
}

fun main(args: Array<String>) = delegated.forEach { println(it) }
