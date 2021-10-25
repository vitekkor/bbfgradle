// Original bug: KT-40308

import kotlin.reflect.KProperty

class SomeType
class OtherType

class SomeDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = "Delegated!"
}
class OtherDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = "Delegated!"
}

class Test {
    companion object {
        val SomeType.test by SomeDelegate()
        val OtherType.test by OtherDelegate()
    }
    
    fun test() {
        println(SomeType().test)
    }
}

fun main() {
    Test().test()
}
