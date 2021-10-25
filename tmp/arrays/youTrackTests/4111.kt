// Original bug: KT-29911

class NullableUpperBoundGenericClass<T : Number?>(val property: T) {
    fun printInt() {
        // This line compiles
        if (property !== null) println(property.toInt())
        // Error: Kotlin: Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type T
        property?.run { println(toInt()) }
    }
}

fun main() {
    NullableUpperBoundGenericClass(0).printInt()
}
