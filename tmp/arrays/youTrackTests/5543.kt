// Original bug: KT-24786

enum class E {
    A, B, C
}

var x = E.A

fun g(): E {
    x = E.B
    return E.C
}

fun main(args: Array<String>) {

    println(when (x) {
        g() -> x
        E.A -> x
        else -> "something else"
    })
}
