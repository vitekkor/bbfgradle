// Original bug: KT-41351

fun foo() {
    if (1 == 1) throw SomeException(1)
}

fun check() {
    try {
        foo()
    } catch (e: SomeException) {
        e.printStackTrace()
    }
}

class SomeException(val param: Int) : Exception()
