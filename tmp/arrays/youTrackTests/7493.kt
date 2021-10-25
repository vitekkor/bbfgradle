// Original bug: KT-18129

import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? = null // null, always null
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {}
}
fun main(args: Array<String>) {
    var name: String? by Delegate()
    name = "Test"
    name.hashCode() // smart cast to not-null
}
