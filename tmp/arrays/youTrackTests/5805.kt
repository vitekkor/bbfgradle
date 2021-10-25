// Original bug: KT-23178

fun main(args: Array<String>) {
    example<Int>()
}
inline fun <reified T: Any> example() {
    println(T::class == "1".toInt()::class) // true
    println("1".toInt()::class == T::class) // false
    println(Int::class.isInstance("1".toInt())) // true
    println(T::class.isInstance("1".toInt())) // false
}
