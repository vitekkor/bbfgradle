// Original bug: KT-22375

package com.acidapestudios.apeapp.core

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty

class ForwardedReadWriteProperty<in R, T>(val targetProperty: KMutableProperty0<T>) : ReadWriteProperty<R, T> {
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return targetProperty.get()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        targetProperty.set(value)
    }
}

operator fun <R, T> KMutableProperty0<T>.provideDelegate(thisRef: R, prop: KProperty<*>): ReadWriteProperty<R, T> {
    return ForwardedReadWriteProperty(this)
}
