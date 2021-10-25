// Original bug: KT-19767

fun foo(): Boolean {
    return M<Int>()?.nulled() == 1
}

class M<T: Any> {
    fun nulled(): T? = null
}

fun main(args: Array<String>) {
    println(foo())
}
