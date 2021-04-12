// Original bug: KT-11825

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class TypeLiteral<T> {
    val type: Type
        get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}

// normal type argument works fine
inline fun <reified T> typeLiteral(): TypeLiteral<T> = object : TypeLiteral<T>() {}

// composing T into List<T> loses reification
inline fun <reified T> listTypeLiteral() = typeLiteral<List<T>>()

fun main(args: Array<String>) {
    // prints "java.lang.String" as expected
    println(typeLiteral<String>().type)
    // prints "java.util.List<? extends T>" instead of "java.util.List<? extends java.lang.String>"
    println(listTypeLiteral<String>().type)
}
