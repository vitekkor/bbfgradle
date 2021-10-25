// Original bug: KT-35166

import kotlin.reflect.KProperty

class C {
    operator fun <T> T.getValue(thisRef: Nothing?, property: KProperty<*>) = this
}

fun run(c: C, block: C.() -> Unit) {
    c.block()
}

fun main() {
    run(C()) {
        val foo by object {
            val fooProp = 42
        }

        object {
            val prop = foo.fooProp
        }
    }
}
