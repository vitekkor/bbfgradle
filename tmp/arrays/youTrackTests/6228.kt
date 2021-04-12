// Original bug: KT-22454

fun main() {
    var x: String? = null

    outer@ do {
        inner@ do {
            break@outer
        } while (x == null)
    } while (true)
    x.length // Unsoundly smartcasted to 'String'
}
