// Original bug: KT-37379

interface A {
    operator fun unaryMinus(): String
}

class B : A {
    override fun unaryMinus(): String = ""
}

fun foo(b: B) {
    -b // unaryMinus for B is not operator function
}
