// Original bug: KT-34043

import kotlin.comparisons.*

class A: Comparable<A> {
    override fun compareTo(a: A): Int {
        return 0
    }
}

class B: Comparable<B> {
    override fun compareTo(a: B): Int {
        return 0
    }
}


fun main(args: Array<String>) {
    println(compareValues(A(), B()))
}
