// Original bug: KT-44530

inline fun <T> identity(block: () -> T): T = block()
inline fun <T> perform(block: () -> T): T = identity(block)
fun main() {
    println(perform { "hello" })
}
