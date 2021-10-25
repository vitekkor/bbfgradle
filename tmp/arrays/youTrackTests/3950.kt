// Original bug: KT-35588

fun boo(): Boolean = true

fun mango() {
    val x = mutableListOf<() -> Unit>()
    // Lift assignment out of 'if'
    if (boo()) {
        x += { println("!") }
    } else {
        return
    }
}

fun papaya() {
    val x = mutableListOf<() -> Unit>()
    // Lift assignment out of 'if'
    if (boo()) {
        x += fun() { println("!") }
    } else {
        return
    }
}
