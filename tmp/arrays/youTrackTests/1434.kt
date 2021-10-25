// Original bug: KT-34856

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.Callable
import java.util.function.Supplier

abstract class TypeRef<T> protected constructor() {
    val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    override fun toString(): String = type.toString()
}

inline fun <reified V> testTypeRef() {
    println("In root ${object : TypeRef<V>() {}}")

    Callable {
        println("In callable ${object : TypeRef<V>() {}}")
    }.call()

    Supplier {
        println("In supplier ${object : TypeRef<V>() {}}")
    }.get()

}

fun main() {
    testTypeRef<List<String>>()
}
