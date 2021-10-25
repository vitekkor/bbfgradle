// Original bug: KT-2570

fun <T> foo(l: List<T>) : List<T> = l

fun test(l: List<out Int>) {
    // should be ok
    val a : List<out Int> = foo(l) //type mismatch on 'l': required List<Int>, found: List<out Int>
    // T should be inferred as 'capture(out Int)', b type should be List<out Int> (to be discussed)
    val b = foo(l)
}
