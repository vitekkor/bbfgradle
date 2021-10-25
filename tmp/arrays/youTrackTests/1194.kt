// Original bug: KT-10876

fun main(args: Array<String>) {
	var list = listOf("a", "b", "c", "d", "e", "f")
	println(list[1..2, 4..5]) //[b, c, e, f], 

}

private operator fun <E> List<E>.get(vararg ranges: IntRange):Collection<E> {
	//loop through ranges and merge results
	return listOf()
}
