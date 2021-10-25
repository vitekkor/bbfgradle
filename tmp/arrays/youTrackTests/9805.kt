// Original bug: KT-8132

fun <T> test(foo: List<T>): T {
    return if (true)
        throw IllegalStateException()
    else
        foo.reduce { left, right -> left } // error: inferred type T is not subtype Nothing
}
