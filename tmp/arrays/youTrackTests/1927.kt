// Original bug: KT-31559

package foo

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Store<T:Any?>(val init: (() -> T)) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = init.invoke()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    }
}

private val cache: Map<String, String> by Store { hashMapOf() } // <-- here comes an compiler error 

// If you uncomment this line the code will compile fine but IDEA will advice to remove <String, String>
// private val cache: Map<String, String> by Store { hashMapOf<String, String>() }

