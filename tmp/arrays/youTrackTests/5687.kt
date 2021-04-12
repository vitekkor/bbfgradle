// Original bug: KT-25432

fun case_1(a: MutableList<out Int?>?) {
    if (a != null) {
        val b = a[0] // no SMARTCAST diagnostic
        if (b != null) {
            b.inc() // Inferend to Int
        }
    }
}
