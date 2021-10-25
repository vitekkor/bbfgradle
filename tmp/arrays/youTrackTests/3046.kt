// Original bug: KT-37058

interface A
interface B

fun foo(a: A) {}
fun foo(b: B) {}

fun bar(a: A) {}

val l: (A) -> Unit
    get() =
        if (1 < 2) {
            ::foo
        } else {
            ::bar
        }
