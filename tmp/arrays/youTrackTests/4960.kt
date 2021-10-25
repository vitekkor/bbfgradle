// Original bug: KT-24656

fun foo(cc: Any) {
    cc as List<Any>
    cc as List<Int?>

    d(wrap(cc)) // error
}

fun d(x: Inv<List<Int>>) {}

class Inv<T>
fun <T> wrap(x: T): Inv<T> = TODO()
