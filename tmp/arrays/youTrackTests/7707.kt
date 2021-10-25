// Original bug: KT-26040

fun bar(a: Int?): Int {
    if (a != null) {
        return a
    }
    return if (a != null) a else 4 // here is warning that a != null is always false
}
