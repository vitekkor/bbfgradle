// Original bug: KT-9871

inline fun String.all(predicate: (Char) -> Boolean): Boolean {
	for (element in this) if (!predicate(element)) return false
	return true
}

inline fun String.forEach(operation: (Char) -> Unit) {
    for (element in this) operation(element)
}
