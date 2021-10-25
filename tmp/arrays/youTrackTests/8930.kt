// Original bug: KT-15480

fun main(args: Array<String>) {
    val list = listOf<String>()

    // compiles
    val (a, b) = list

    if (true) {
        // compiles
        val (c, d) = list
    }

    if (true) {
        // doesn't compile
        val (e, f) = list
    } else {
    }
}
