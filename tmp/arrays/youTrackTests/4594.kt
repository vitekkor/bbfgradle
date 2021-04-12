// Original bug: KT-35572

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Test {
    var number by TestProperty() // "Set breakpoint -> Kotlin Field Watchpoint"
}

fun main() {
    val test = Test()
    test.number = 44
}

// just a dummy delegate
class TestProperty<in R> : ReadWriteProperty<R, Int> {
    var value: Int = 0
    override fun getValue(thisRef: R, property: KProperty<*>): Int {
        return value
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: Int) {
        this.value = value
    }
}
