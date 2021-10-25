// Original bug: KT-35533

inline class SomeId(val id: String) {
    fun length(): Int = id.length
}

fun main() {
    val str: String? = "Hello"
    func(str?.let { SomeId(it) })?.length()

    //str?.let { SomeId(it) }?.length() // it works
}

fun <T> func(value: T?) = value
