// Original bug: KT-30176

import kotlin.reflect.KClass

infix fun<X:Any> X?.iz(_null_: Nothing?)   { /* implementation */ }
infix fun<X:Any> X?.iz(klass: KClass<Any>) { /* implementation */ }
infix fun<X:Any> X?.iz(marker: Marker)     { /* implementation */ }

abstract class Marker

fun main(args: Array<String>) {
    val x: Any? = null
    x iz null
}
