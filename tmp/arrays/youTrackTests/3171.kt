// Original bug: KT-39163

fun main() {
	val f = makeMagicAlgorithm(100)
	val result = (0..400_000_000).reduce(f)
	println(result)
}

fun makeMagicAlgorithm(factor: Int): (Int, Int) -> Int {
	return { a, b -> a + b * factor }
}

