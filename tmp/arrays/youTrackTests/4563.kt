// Original bug: KT-31679

import kotlin.reflect.*

class Cell<T>(val sink: () -> T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return sink()
    }
}

fun <TValue> cell(calculateValue: () -> TValue): Cell<TValue> {
    return Cell(calculateValue)
}

private val l by cell {
    object {
        val x = 42
        val y = 2
    }
}

fun main() {
    println(l.x) // IDE shows error here
}
