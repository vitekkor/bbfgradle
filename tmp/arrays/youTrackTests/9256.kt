// Original bug: KT-15437

import kotlin.reflect.KProperty

class Delegate {
    operator fun provideDelegate(instance: Any?, property: KProperty<*>): Delegate = this
    operator fun getValue(instance: Any?, property: KProperty<*>) = "OK"
}

val result: String by Delegate()

fun main(args: Array<String>) {
    println(result)
}
