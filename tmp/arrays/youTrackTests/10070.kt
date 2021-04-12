// Original bug: KT-9754

class Your

fun Your.foo() = Any()

fun test(your: Your?) {
    (your?.foo() as? Any)?.let {}
    // strange smart cast to 'Your' at this point
    your.hashCode()
}
