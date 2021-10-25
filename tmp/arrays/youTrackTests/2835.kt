// Original bug: KT-25368

@Deprecated("", replaceWith = ReplaceWith("C"))
private typealias A = C

private class C {
    companion object {
        val compVal = 1
    }
}

fun f() {
    val v: A  // intention is suggested
    val v2 = A.compVal // no intention available
}

