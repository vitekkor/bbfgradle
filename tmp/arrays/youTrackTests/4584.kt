// Original bug: KT-35546

fun main() {
    val lambda1: () -> Unit = {}
    val lambda2: () -> Unit = { lambda1() }
    val lambda3: (() -> Unit) -> Unit = { it() }

    lambda3(lambda2) // breakpoint
}
