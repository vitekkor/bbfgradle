// Original bug: KT-44461

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KType

open class KTypeToken<T> {
    val kType: KType = this::class.supertypes.first { it.classifier == KTypeToken::class }.arguments[0].type!!
    val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}

inline fun <reified T> typeToken(): KTypeToken<T> = object : KTypeToken<T>() {}

fun main() {
    val token1 = object : KTypeToken<List<String>>() {}
    val token2 = typeToken<List<String>>()
    println("Java reflection gives similar results (captures all information about passed type):")
    println(token1.type)
    println(token2.type)
    println(token1.type == token2.type)
    println()
    println("Kotlin reflection is broken for reified type:")
    println(token1.kType)
    println(token2.kType)
    println(token1.kType == token2.kType)
}
