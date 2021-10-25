// Original bug: KT-37655

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main(args: Array<String>) {
}

class Foo() {

    var bar: Bar? by myProp<Bar?>(null) { it } // OK, but with "remove explicit type arguments" IDE warning

    var bar2: Bar? by myProp(null) { it } // e: Property delegate must have a 'setValue...'
    var bar3: Bar? by myProp(null) { it?.copy() } // e: Type inference failed: Cannot infer type parameter T in fun <T> myProp(initialValue: T, copy: (T) -> T): ReadWriteProperty<Foo, T>

}

interface Bar

fun Bar.copy(): Bar {
    return this
}

fun <T> myProp(initialValue: T, copy: (T) -> T): ReadWriteProperty<Foo, T> {
    return object : ReadWriteProperty<Foo, T> {

        override fun getValue(thisRef: Foo, property: KProperty<*>): T = initialValue
        override fun setValue(thisRef: Foo, property: KProperty<*>, value: T) {
        }
    }
}
