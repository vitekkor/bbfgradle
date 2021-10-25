// Original bug: KT-27836

inline fun <reified T> trickMe(): String? {
    return T::class.simpleName
}

fun main() {
    println(Int::class.simpleName) //1
    println(trickMe<Int>()) //2
}
