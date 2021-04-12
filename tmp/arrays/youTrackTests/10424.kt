// Original bug: KT-5772

class A : Comparable<A> {
    override fun compareTo(other: A): Int = 0
}

fun box(): String {
    var x: Comparable<A> = A()
    x < A()
    return "OK"
}
