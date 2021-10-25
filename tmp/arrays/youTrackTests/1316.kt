// Original bug: KT-17103

abstract class KTypeRef<T> protected constructor() {
    var type = this::class.supertypes[0].arguments[0].type
        ?: throw IllegalArgumentException("Internal error: TypeReference constructed without actual type information")
}
inline fun <reified T> test() {
    println(object : KTypeRef<T>() {}.type)
}

fun main() {
    test<List<Int>>()
}
