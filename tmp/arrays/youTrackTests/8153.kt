// Original bug: KT-21371

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Order

val Order.lalala by LazyForReceiver<Order, String> {
    "hi there"}

class LazyForReceiver<in This : Any, out Value>(val make: This.() -> Value) : ReadOnlyProperty<This, Value> {
    override fun getValue(thisRef: This, property: KProperty<*>): Value {
        throw Exception(".....")
    }
}
