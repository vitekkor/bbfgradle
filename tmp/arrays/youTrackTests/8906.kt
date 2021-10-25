// Original bug: KT-16864

import kotlin.reflect.KProperty

object Whatever {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>) = "Whatever"
}

fun main(args: Array<String>) {
    val key by Whatever
    myRun {
        val response = object {
            val keys = key
        }
    }
}

fun myRun(x: () -> Unit) {
    x()
}

