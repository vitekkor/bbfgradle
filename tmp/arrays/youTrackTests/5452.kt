// Original bug: KT-25527

import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val delegate = Delegate()
    foo(delegate)
    println(delegate.prop) // Real usage not detected inside `foo`
}

fun foo(delegate: Delegate) {
    var p by delegate // Variable is assigned but never accessed
    p = "Hello" // Value is never used
}

class Delegate {
    var prop: String = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return prop
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        prop = value
    }
}
