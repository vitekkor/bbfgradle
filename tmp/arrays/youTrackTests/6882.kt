// Original bug: KT-28847

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

class Foo {
    var property: String? by equalVetoingObservable<String?>(null) {
        !it.isNullOrEmpty()
        foo.bar()
    }

    private val foo = object : Any() {
        fun bar() {
            !property.isNullOrEmpty()
        }

    }

    inline fun <T> equalVetoingObservable(initialValue: T, crossinline onChange: (newValue: T) -> Unit) =
        object : ObservableProperty<T>(initialValue) {
            override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T) = oldValue != newValue
            override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(newValue)
        }
}
