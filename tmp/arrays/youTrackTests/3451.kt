// Original bug: KT-33363

fun main(args: Array<String>) {
    val x = 2
    when (x) {
        // breakpoint here
        1 -> print("x == 1")
        // and here
        2 -> print("x == 2")
        // and here
        else -> {
            print("x is neither 1 nor 2")
        }
    }
}
