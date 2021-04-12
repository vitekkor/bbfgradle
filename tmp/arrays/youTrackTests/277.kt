// Original bug: KT-45431

import kotlin.reflect.KProperty

class Test {
    companion object {
        private operator fun String.provideDelegate(thisRef: Any?, property: KProperty<*>) = lazy { "$this world" }
        val test by "Hello"
    }
}

fun main() {
    println(Test.test)
}
