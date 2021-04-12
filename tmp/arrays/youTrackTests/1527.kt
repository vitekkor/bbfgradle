// Original bug: KT-36203

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.typeOf

data class Definition(
    val name: String
)

@ExperimentalStdlibApi
fun main() {
    val parsed1: List<Definition> = parseGood("")
    val parsed2: List<Definition> = parseBad("")
}

@ExperimentalStdlibApi
private inline fun <reified T> parseGood(blob: String): T {
    return readValue(blob)
}

@ExperimentalStdlibApi
private inline fun <reified T> parseBad(blob: String): List<T> {
    return readValue(blob)
}

@ExperimentalStdlibApi
private inline fun <reified T> readValue(blob: String): T {
    println(typeOf<T>())
    val type = object : TypeHolder<T>() {}.type
    println(type)
    //Do magic with type and return a T
    return listOf(Definition("foo")) as T
}

open class TypeHolder<T> protected constructor() {
    private var _type: Type
    val type get() = _type

    init {
        val superClass = javaClass.genericSuperclass
        _type = (superClass as ParameterizedType).actualTypeArguments[0]
    }
}
