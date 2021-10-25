// Original bug: KT-17552

import java.util.concurrent.ConcurrentHashMap
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class AttachedComputedShit<in Host : Any, out Shit>(val create: (Host) -> Shit) : ReadOnlyProperty<Host, Shit> {
    private val NULL = Any()

    override fun getValue(thisRef: Host, property: KProperty<*>): Shit {
        val propertyNameToValue = thisRefToPropertyNameToValue.computeIfAbsent(thisRef) {
            ConcurrentHashMap()
        }
        val res = propertyNameToValue.computeIfAbsent(property.name) {
            create(thisRef) ?: NULL
        }
        @Suppress("UNCHECKED_CAST")
        return (if (res === NULL) null else res) as Shit
    }

    companion object {
        val thisRefToPropertyNameToValue = ConcurrentHashMap<Any, MutableMap<String, Any>>()
    }
}
