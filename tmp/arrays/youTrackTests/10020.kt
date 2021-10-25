// Original bug: KT-6483

import java.lang.reflect.*

open class TypeLiteral<T> {
    val type: Type
        get() = (javaClass.getGenericSuperclass() as ParameterizedType).getActualTypeArguments()[0]
}
inline fun <reified T> typeLiteral(): TypeLiteral<T> = object : TypeLiteral<T>() {}

fun main(args: Array<String>) {
  println(typeLiteral<List<String>>().type) // prints List now, should print List<? extends String>
}
