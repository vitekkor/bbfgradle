// Original bug: KT-27784

import kotlin.reflect.full.createType

class C<T> {
    inner class Inner
}

fun main(args: Array<String>) {
    val cls = C.Inner::class
    if (cls.typeParameters.isEmpty()) {
        cls.createType()
    }
}
