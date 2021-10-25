// Original bug: KT-31422

class FreeableWrapper<T>(var value: T?) {
    val notNullValue: T get() = value!!
    fun free() {
        value = null
    }
}

fun main() {
    val wrapper = FreeableWrapper(listOf(0, 1, 2, 3))
    val data: List<Int> by wrapper::notNullValue
    println(data)
    wrapper.free()
    println(data)
}
