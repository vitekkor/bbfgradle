// Original bug: KT-34838

import kotlin.reflect.KProperty

var delegate by object {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>) = Any()

    operator fun setValue(nothing: Nothing?, property: KProperty<*>, any: Any) {

    }
}
