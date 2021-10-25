// Original bug: KT-34024

import kotlin.reflect.full.*
import kotlin.reflect.jvm.*

inline class Key(val value: Int)

class Service {
    suspend fun getSomeStuff(key: Key) = key.toString()
}

fun main() {
    val method = Service::class.declaredFunctions.first()
    println(method.javaMethod)
}
