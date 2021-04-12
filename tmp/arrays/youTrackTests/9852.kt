// Original bug: KT-11163

operator fun Int.compareTo(c: Char) = 0

fun foo(x: Int, y: Char) {
    if (x < y) {
        throw Error()
    }
}

fun main(args: Array<String>) {
    foo(42, 'a')
}
