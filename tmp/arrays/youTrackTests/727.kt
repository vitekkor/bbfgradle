// Original bug: KT-41874

import kotlin.reflect.KProperty
 
open class Delegate {
 
    inline operator fun getValue(receiver: Any, property: KProperty<*>): Any =
            throw IllegalStateException()
 
    inline operator fun setValue(receiver: Any, property: KProperty<*>, value: Any): Unit =
            throw IllegalStateException()
}
 
class SubDelegate : Delegate()
 
// compilation is successful.
val Any.baz: Any by SubDelegate()
