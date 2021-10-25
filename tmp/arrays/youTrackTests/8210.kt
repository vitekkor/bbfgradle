// Original bug: KT-22375

package com.acidapestudios.apeapp.core

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

class ForwardedReadOnlyProperty<in R, out T>(val targetProperty: KProperty0<T>) : ReadOnlyProperty<R, T> {
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return targetProperty.get()
    }
}

operator fun <R, T> KProperty0<T>.provideDelegate(thisRef: R, prop: KProperty<*>): ReadOnlyProperty<R, T> {
    return ForwardedReadOnlyProperty(this)
}
