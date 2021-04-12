// Original bug: KT-28264

fun <A> test1(value: A) {
    if (value != null) value.javaClass else null        // compiler error
}

fun test2(value: Any?) {
    if (value != null) value.javaClass else null        // ok
}
