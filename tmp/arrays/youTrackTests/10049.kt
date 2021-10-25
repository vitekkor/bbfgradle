// Original bug: KT-9771

fun main(args: Array<String>) {
    val a = arrayListOf(A(0), A(1), A(2), A(3), A(4), A(5), A(6), A(7), A(8), A(9), A(10), A(11), A(12), A(13), A(14))
    val b = a.sorted()
    println(b) // [A(b=0), A(b=1), A(b=10), A(b=11), A(b=12), A(b=13), A(b=14), A(b=2), A(b=3), A(b=4), A(b=5), A(b=6), A(b=7), A(b=8), A(b=9)]
}

data class A(val b: Int) : Comparable<A> {
	override fun compareTo(other: A): Int {
		return b.compareTo(other.b)
	}
}
