// Original bug: KT-28334

fun case_1(b: Comparable<*>?) {
    if (b is Byte?) {
        b!!.dec() // Smart cast to 'Byte' is impossible, because 'b!!' is a complex expression
    }
}
