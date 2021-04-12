// Original bug: KT-27425

fun wrongBreakLabel() {
    var n = 10
    while (n < 10) {
        when (n) {
            4 -> {
                n++
            }
            5 -> {
                println(5)
                n++
            }
            else -> {
                n++
                listOf(1).all { true }
            }
        }
    }
}
