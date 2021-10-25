// Original bug: KT-36044

fun <A> select(x: A, f: () -> A) = f()
fun <B> map(f: () -> B) = f()

fun main() {
    select('a', map { { "" } }) // Any in OI, error in NI (Required: () â Char, Found: () â String)
}
