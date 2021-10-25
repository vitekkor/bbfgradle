// Original bug: KT-19031

private fun when1(number: Int) = when (number) {
	0 -> "zero"
	1 -> "one"
	else -> "other"
}

private fun when2(number: Int): String {
	return when (number) {
		0 -> "zero"
		1 -> "one"
		else -> "other"
	}
}

private fun when3(number: Int): String {
	when (number) {
		0 -> return "zero"
		1 -> return "one"
		else -> return "other"
	}
}
