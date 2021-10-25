// Original bug: KT-23482

val <T> T.k: () -> T get() = { -> this }

fun test(x: Int) {
    x.k()?.toString() // here type for expression `x.k()` has uninferred type `T` and FE error should be reported
}
