// Original bug: KT-23413

import kotlin.reflect.KProperty

object Delegate {
    operator fun getValue(thiz: Any?, property: KProperty<*>): String {
        return property.name + ":" + property.returnType
    }
}

val x by Delegate

fun main(args: Array<String>) {
    println(x)
    run {
        val y by Delegate
        println(y)
    }
}
