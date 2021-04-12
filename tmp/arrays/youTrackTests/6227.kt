// Original bug: KT-22454

fun main(args: Array<String>) {
    var x: String? = null

    outer@ while (true) {
        inner@ while (x == null) {
            break@outer
        }
    }
    x.length // Unsoundly smartcasted to 'String'
}
