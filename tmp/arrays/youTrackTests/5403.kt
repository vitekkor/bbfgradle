// Original bug: KT-33551

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Delegate : ReadWriteProperty<Any?, Boolean> {
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>) = true
}

class Foo {
    init {
        bar()
    }

    fun bar() {
        indirectlyCalledInInit
    }

    val indirectlyCalledInInit by Delegate()
}

fun main() {
    Foo()
}
