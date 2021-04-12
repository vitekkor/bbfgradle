// Original bug: KT-20359

class My {
    fun add(s: String) {}
}

fun main(args: Array<String>) {
    val my = My()
    for /* Replace with += */ (arg in args) {
        my.add(arg)
    }
}
