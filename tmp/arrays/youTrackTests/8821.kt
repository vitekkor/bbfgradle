// Original bug: KT-18672

import kotlin.reflect.full.*
import kotlin.reflect.jvm.javaType

interface OK
class X : OK

fun main(args: Array<String>) {
    X::class.allSupertypes.map { it.javaType }
}
