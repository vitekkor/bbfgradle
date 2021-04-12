// Original bug: KT-10876

fun main(args: Array<String>) {
	var list = listOf("a", "b", "c", "d", "f")
	println(list[1..4 step 2]) //[b, d]

}

private operator fun <E> List<E>.get(vararg ranges: IntProgression):Collection<E> {
	//loop through Int progressions and handle the steps
	return listOf()
}
