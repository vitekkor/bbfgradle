// Original bug: KT-13606

import kotlin.reflect.KProperty

class ExampleDelegate(val value: Int) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = value
}

val example by ExampleDelegate(1000) // number larger than 127 (no box cache)
