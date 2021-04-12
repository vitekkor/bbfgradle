// Original bug: KT-16441

import kotlin.reflect.KProperty

class Delegate {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>) = this
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = Unit
}

class TestClass {
    companion object {
        val test by Delegate()
    }
}

fun main(args: Array<String>) {
    TestClass.test
}
