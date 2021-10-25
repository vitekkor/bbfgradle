// Original bug: KT-41105

import kotlin.reflect.KProperty

open class Property<T, out A> {
    @Suppress("NOTHING_TO_INLINE")
    inline operator fun getValue(thisRef: Any?, property: KProperty<*>): T = TODO()

    @Suppress("NOTHING_TO_INLINE")
    inline operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Unit = TODO()
}


