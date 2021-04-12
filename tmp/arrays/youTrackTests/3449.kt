// Original bug: KT-33364

fun main() {
    val param = 3

    //breakpoint here
    when(param) {
        1 -> print("A")
        2 -> print("B")
        else -> print("C")
    }

    // breakpoint here
    when {
        param == 1 -> print("A")
        param == 2 -> print("B")
        else -> print("C")
    }
}
