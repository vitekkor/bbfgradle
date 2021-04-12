// Original bug: KT-26293

import kotlin.reflect.jvm.javaType

interface A {
    suspend fun f(): String
}

fun main(args: Array<String>) {
    val member = A::class.members.first()

    println(member.returnType) // String - Ok
    println(member.returnType.javaType) // Object - Wrong
}
