// Original bug: KT-24217

fun <A, I, R> all(f: (A) -> I, g: (I) -> R) {}
fun <T> id(x: T): T = x

fun foo(intToInt: (Int) -> Int) {
    all(intToInt, ::id) // OK
    all(::id, intToInt) // Error
}
