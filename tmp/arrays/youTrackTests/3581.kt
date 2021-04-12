// Original bug: KT-37406

// error:  Type 'TypeVariable(T)' has no method 'getValue(Build_gradle, KProperty<*>)' and thus it cannot serve as a delegate
val imageVersions: String by myDelegate("string")

fun <T> myDelegate(initializer: T): Delegate<T> = Delegate(initializer)

class Delegate<T>(initializer: T) {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): T {
        return "$thisRef, thank you for delegating '${property.name}' to me!" as T
    }
}
