// Original bug: KT-21368

import kotlin.properties.Delegates.notNull
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Something(val name: String)

// Please, make following work:
// val Something.len by lazyWithReceiver {name.length} // Error: Type inference failed: Not enough information to infer parameter Value

val Something.len by lazyWithReceiver<Something, Int> {name.length} // This works

object Test1 {
    @JvmStatic fun main(args: Array<String>) {
        println(Something("foo").len)
    }
}

fun <This : Any, Value> lazyWithReceiver(make: This.() -> Value) = object : ReadOnlyProperty<This, Value> {
    private var thisRef by notNull<This>()
    private val value: Value by lazy {make(thisRef)}

    override fun getValue(thisRef: This, property: KProperty<*>): Value {
        this.thisRef = thisRef
        return value
    }
}
