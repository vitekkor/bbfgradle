// Original bug: KT-5730

import kotlin.reflect.KProperty

class D {
    operator fun <T> getValue(x: Any?, p: KProperty<*>): T {
        return null as T
    }
}

val b: Int by D()
