// Original bug: KT-45907

fun main() = Session().use {
    data class Data(val n: Int)
    println(load<Data>())
}

class Session: AutoCloseable {
    override fun close() {}
}

inline fun <reified T> load(): T {
    return T::class.constructors.first().call(123)
}
