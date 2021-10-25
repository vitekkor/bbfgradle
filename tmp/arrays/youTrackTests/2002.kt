// Original bug: KT-40412

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CaptureContext<A>(val capture: (A) -> Unit) : ReadOnlyProperty<A, () -> Unit> {
    override fun getValue(thisRef: A, property: KProperty<*>) = { -> capture(thisRef) }
}

operator fun <A> ((A) -> Unit).provideDelegate(thisRef: A, property: KProperty<*>) = CaptureContext(this)

fun right(arg: Right) {}
class Right { val prop: () -> Unit by ::right }
