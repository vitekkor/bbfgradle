// Original bug: KT-19031

private fun if1(number: Int) = if (number == 0) {
	"zero"
} else if (number == 1) {
	"one"
} else {
	"other"
}

private fun if2(number: Int): String {
	return if (number == 0) {
		"zero"
	} else if (number == 1) {
		"one"
	} else {
		"other"
	}
}

private fun if3(number: Int): String {
	if (number == 0) {
		return "zero"
	} else if (number == 1) {
		return "one"
	} else {
		return "other"
	}
}
