// Original bug: KT-28333

fun case_1(x: Int?) {
    operator fun Nothing.invoke(x: Any) {}

    val y: Int
    while (true) {
        break(x!!)
    }
    x.inv() // Unsound smartcast, NPE
}
