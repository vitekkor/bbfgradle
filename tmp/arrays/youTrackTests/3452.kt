// Original bug: KT-33363

fun max(a: Int, b: Int) {
    val max: Int
    if (a > b) {
        //breakpoint here
        max = a
    } else {
        //and here
        max = b
    }
}

fun main() {
    max(3, 5)
}
