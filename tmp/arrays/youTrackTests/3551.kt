// Original bug: KT-28224

fun test(x: Int, b: Boolean) {
    val foo = if (x == 1)
        if (b) 1 else {
            2 // comment
        }
    else 0

    val bar = when (x) {
        1 ->
            if (b) 1 else {
                2 // comment
            }
        else ->
            0
    }
}
