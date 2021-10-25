// Original bug: KT-35995

fun test(i: Int) {
    val p: (String) -> Boolean =
        if (i == 1) { s/*caret*/ -> true } else { s -> false }

    val p2: (String) -> Boolean =
        when (i) {
            1 -> { s/*caret*/ -> true }
            else -> { s -> false }
        }
}
