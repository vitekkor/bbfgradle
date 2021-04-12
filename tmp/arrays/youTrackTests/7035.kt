// Original bug: KT-15751

fun main(args: Array<String>) {
	fff()
}

fun fff(): Int {
	val y = 0
	return 0.also {
		fun increase(x: Int): Int =
			x + y
		listOf(0).mapNotNull { something(::increase, it) }
	}
}

fun something(increase: (Int) -> Int, x: Int): Int? {
	return null
}
