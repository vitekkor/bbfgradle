// Original bug: KT-18947

private class IntIterable(private vararg val elements: Int) : Iterable<Int> {
	override fun iterator(): IntIterator = elements.iterator()
}

private inline fun <T> test(elements: Iterable<T>, function: (T) -> Unit) {
	for (element in elements) {
		function(element)
	}
}

fun main(args: Array<String>) {
	test(IntIterable(1, 2, 3)) { println(it) }
}
