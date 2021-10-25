// Original bug: KT-22649

import java.util.function.Consumer

class Thing {
    var foo: Int = 0
}

inline var Thing.bar: Boolean
    get() = foo == 1
    set(value) {
        foo = if (value) 1 else 0
    }

fun main(args: Array<String>) {
    val consumer = Consumer<Boolean> { value ->
        Thing().bar = value
    }
}
