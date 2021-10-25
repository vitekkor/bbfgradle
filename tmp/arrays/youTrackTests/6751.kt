// Original bug: KT-14576

interface A
interface B

fun prepare(x: A) = x

fun <T : Any> select(x1: T?, x2: T) = x2

fun test(x: A) {
    val xx = select(x as? B, prepare(x)) // xx: Any
}
