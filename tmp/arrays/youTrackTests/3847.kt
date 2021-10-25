// Original bug: KT-34295

fun foo(x: Boolean) {
    val x1 = { y: Int -> println(y) } as Int.() -> Unit
    val x2 = { y: Int -> println(y) } as (Int) -> Unit

    val bar = when (x) {
        true -> x1
        false -> x2
    } // always (Int) -> Unit
}
