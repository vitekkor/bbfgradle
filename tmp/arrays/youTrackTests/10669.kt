// Original bug: KT-2843

fun f() {
    val (x, y) = Pair(1, 2) // 'x' and 'y' marked as unused: incorrect
    val (xx, yy) = Pair(x, y) // 'xx' and 'yy' not marked as unused: correct
    val p = Pair(xx, yy)
}
