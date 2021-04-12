// Original bug: KT-37406

import kotlin.reflect.KProperty

val imageVersions: String by myDelegate("string")

fun <T> myDelegate(initializer: T): Delegate<T> = Delegate(initializer)

class Delegate<T>(initializer: T) {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): T {
        return "$thisRef, thank you for delegating '${property.name}' to me!" as T
    }
}
operator fun <T : Any> T.provideDelegate(receiver: Any?, property: KProperty<*>): T = this
