// Original bug: KT-11677

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class TypeLiteral<T> {
  val type: Type
    get() = (javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[0]
}

// normal inline function works fine
inline fun <reified T> typeLiteral(): TypeLiteral<T> = object : TypeLiteral<T>() {}

// nested lambda loses reification of T
inline fun <reified T> brokenTypeLiteral(): TypeLiteral<T> = "".run { typeLiteral<T>() }

fun main(args: Array<String>) {
  // prints "java.util.List<? extends java.lang.String>" as expected
  println(typeLiteral<List<String>>().type)
  // prints only "T"
  println(brokenTypeLiteral<List<String>>().type)
}
