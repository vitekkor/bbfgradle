// Original bug: KT-11316

import kotlin.reflect.KProperty

object HashCodeComputer {
    operator fun getValue(x: Any?, p: KProperty<*>): Int {
        return p.hashCode()
    }
}

val h: Int by HashCodeComputer

fun main(args: Array<String>) {
    println(h)
}
