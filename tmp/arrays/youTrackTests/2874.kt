// Original bug: KT-40057

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

operator fun <C, T> T.provideDelegate(thisRef: C, property: KProperty<*>) =
    object : ReadOnlyProperty<C, T> {
        override operator fun getValue(thisRef: C, property: KProperty<*>) = this@provideDelegate
    }

val foo by 1
