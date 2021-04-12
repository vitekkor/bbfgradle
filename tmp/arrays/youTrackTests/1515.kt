// Original bug: KT-17551

abstract class Generic<T>

inline fun <reified T> capture() = object : Generic<T>() {}

fun main(args: Array<String>) {
    println(capture<String>().javaClass.kotlin.supertypes[0].arguments[0].type); // prints "[ERROR : Unknown type parameter 0]"
    
    println(object : Generic<String>(){}.javaClass.kotlin.supertypes[0].arguments[0].type); // prints "kotlin.String"
}
