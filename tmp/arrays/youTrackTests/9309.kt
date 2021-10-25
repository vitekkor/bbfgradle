// Original bug: KT-14824

import org.jetbrains.kotlin.container.*

class MyClass {
}

fun test() {
    val container = composeContainer("name") {
        useImpl<MyClass>()
    }
}
