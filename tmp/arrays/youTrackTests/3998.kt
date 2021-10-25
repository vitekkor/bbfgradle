// Original bug: KT-28334

fun case_1(b: Any?) {
    if (b is Byte?) {
        b!!.dec() // OK
    }
}

fun case_2(b: Comparable<Byte>?) {
    if (b is Byte?) {
        b!!.dec() // OK
    }
}
