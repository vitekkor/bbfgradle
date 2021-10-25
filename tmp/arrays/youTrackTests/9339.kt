// Original bug: KT-14505

import kotlin.reflect.jvm.*

interface H<T> {
    val parent : T?
}

interface A : H<A>

fun main(args: Array<String>) {
    val property = A::parent
    println(property.returnType)           // "A?", ok
    println(property.returnType.javaType)  // "T", wrong!
}
