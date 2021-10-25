// Original bug: KT-14626

interface A

sealed class B : A {
    class B1 : B()
}

fun f(a: A) =
        if (a is B) {
            when (a) {
                is B.B1 -> "B1"
            }
        } else {
            "Not a B"
        }
