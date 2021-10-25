// Original bug: KT-35868

inline fun <reified T: Any> foo(): T = null as T

fun main() {
    val result: Any = try {
        foo()
    } catch (e: Exception) {
        throw Exception()
    }
}
