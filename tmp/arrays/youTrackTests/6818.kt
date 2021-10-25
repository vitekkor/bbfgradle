// Original bug: KT-10660

package de.dizayndesign.utils.overlap2d

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Osman on 03.08.2015.
 *
 */

class O2dInjectionClosure<PropType : Any>(val meta : InjectDelegationMeta, public var initializer : (propName : String) -> PropType)
    : ReadOnlyProperty<Any?, PropType> {

    init {
        meta.injectClosure = this
    }

    public var value: PropType? = null
        private set


    public inline fun appendInitializer(crossinline appended: (PropType) -> PropType) {
        val curInitializer = initializer
        initializer = { propName -> appended(curInitializer(propName)) }
    }

    fun doInjection() {
        try {
            value = initializer("NO PROPERTY NAME GIVEN")
        } catch (e : TypeCastException) {
            error("injection failed for property!")
        }
    }

    public override fun getValue(thisRef: Any?, property: KProperty<*>): PropType = value!!
}

class InjectDelegationMeta {
    var injectClosure : O2dInjectionClosure<*> by Delegates.notNull()
}
