// Original bug: KT-27828

private inline fun <reified T: Any> type() = T::class

fun main(args: Array<String>) {
    println(type<Byte>().simpleName)
    println(type<Short>().simpleName)
    println(type<Int>().simpleName)
    println(type<Long>().simpleName)
    println(type<Float>().simpleName)
    println(type<Double>().simpleName)

    println(type<Char>().simpleName)
    println(type<Boolean>().simpleName)
}
