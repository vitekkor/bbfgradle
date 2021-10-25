// Original bug: KT-29203

import kotlin.reflect.*
import kotlin.reflect.jvm.*

object O

operator fun O.getValue(thisRef: Any?, property: KProperty<*>): String {
//    return property.getter.returnType.toString()    // this works well and returns "kotlin.String"
    return property.getter.returnType.javaType.toString()    // this doesn't work and always returns "void" for any types
}

fun main() {
    val variableName by O
    println(variableName)
}
