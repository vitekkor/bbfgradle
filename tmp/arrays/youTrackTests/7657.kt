// Original bug: KT-26192

import kotlin.reflect.KProperty

object Delegate {
    lateinit var prop: KProperty<*>

    operator fun provideDelegate(thiz: Any?, p: KProperty<*>): Delegate {
        prop = p
        return this
    }

    operator fun getValue(x: Any?, p: KProperty<*>) { }
}

val x: Unit by Delegate

fun box(): String {
    x
    return "OK"
}

fun main(args: Array<String>) {
    println(box())
}
