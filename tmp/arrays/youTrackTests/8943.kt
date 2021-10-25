// Original bug: KT-17675

private inline fun generateArray(first: Boolean, second: Boolean, third: Boolean): IntArray {
	val intArray = IntArray(3)
	intArray[0] = if (first) 1 else 0
	intArray[1] = if (second) 2 else 0
	if (third) // or like this
		intArray[2] = 3
	return intArray
}

fun main(args: Array<String>) {
	val intArray = generateArray(false, true, true)
}
