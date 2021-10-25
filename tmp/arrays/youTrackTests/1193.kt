// Original bug: KT-10876

fun main(args: Array<String>) {
	var list = listOf("a", "b", "c", "d", "e")
	println(list[1..2]) //[b, c]
}

private operator fun <E> List<E>.get(range: IntRange):Collection<E> {
	return this.subList(range.first, range.last + 1)
}
