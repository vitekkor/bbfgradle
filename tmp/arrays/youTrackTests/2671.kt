// Original bug: KT-32303

inline fun <reified T> x(): () -> Unit {
    return  { println(T::class) }
}

fun main() {
    x<ByteArray>()()
    x<String>()()
    println(ByteArray::class)
}
