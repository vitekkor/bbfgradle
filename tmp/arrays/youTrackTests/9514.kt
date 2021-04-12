// Original bug: KT-12967

data class Box<T>(val element: T) {
    fun get(): T = element
}

fun main(args: Array<String>) {
    println(Box<String>::get)      // ok
    println(Box<String>::element)  // fail
}
