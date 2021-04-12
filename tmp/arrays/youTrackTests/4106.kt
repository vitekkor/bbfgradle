// Original bug: KT-32290

fun Int.extension(foo: (() -> Unit)?): Int {
    return this
}

fun test() {
    2.extension(
            foo = if (1 == 2) {
                { // <- compiler error here
                    println(123)
                }
            } else {
                null
            }
    )
}
