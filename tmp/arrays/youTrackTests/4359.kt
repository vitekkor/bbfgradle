// Original bug: KT-37077

import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

class Delegate {
    operator fun getValue(t: Any?, p: KProperty<*>): Int = (p as KProperty0<Int>).get()
}


fun main(args: Array<String>) {
    val prop: Int by Delegate()
    println(prop) 
}
