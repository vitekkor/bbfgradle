// Original bug: KT-36775

package test

fun test(x: Int, y: Int): String {
    val result: String
    if (x == 6) {
        if (y == 6) {
            result = "a"
        } else {
            result = "b"
        }
    } else {
        result = "c"
    }
    return result
}
