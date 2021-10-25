// Original bug: KT-23748

interface A

class B : A

fun test(l: List<Int>, a: A?) {
    val b: List<A> = l.mapTo(ArrayList(12)) {
        val aa = a ?: B() // `a ?: B()` expression type in IDE: A
        println(a ?: B()) // `a ?: B()` expression type in IDE: Any?
        a ?: B() // `a ?: B()` expression type in IDE: Any
    }
}
