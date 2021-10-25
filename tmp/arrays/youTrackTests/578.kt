// Original bug: KT-42536

fun bar(expected: Any?) {} // (1)
fun bar(expected: Long) {} // (2)

fun <T> id(x: T) = x

fun main() {
    bar(id(1)) // is resolved to (2) only in the new inference
    bar(1) // is resolved to (2) in the old type inference too
}
