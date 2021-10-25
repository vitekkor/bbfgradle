// Original bug: KT-15871

inline fun <T> getAndCheck(getFirst: () -> T, getSecond: () -> T) =
    getFirst() == getSecond()

fun getAndCheckInt(a: Int, b: Int) =
    getAndCheck({ a }, { b })
