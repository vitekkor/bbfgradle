// Original bug: KT-14504

import kotlin.reflect.KProperty

class Foo

class Bar(private val foo: Foo) {
    operator fun Foo.getValue(thisRef: Any, kProperty: KProperty<*>) = 42

    val x by foo
}
