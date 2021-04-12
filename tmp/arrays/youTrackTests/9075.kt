// Original bug: KT-16315

fun caller(): Any? {
	testing {
		return "I will be returned"
	}
	return "I won't be returned, but can't compile without this line"
}

inline fun testing(block: () -> Unit) {
	block()
}
