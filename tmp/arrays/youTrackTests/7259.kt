// Original bug: KT-27070

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Foo {
    val a: Int = 0
    val b by Delegate(0)
}

inline class Delegate(val ignored: Int): ReadOnlyProperty<Foo, Int> {
    override fun getValue(thisRef: Foo, property: KProperty<*>): Int {
        return thisRef.a
    }
}
