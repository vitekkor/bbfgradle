// Original bug: KT-13468

fun foo(): String {
	var current: String? = null
	current = if (current == null) "bar" else current
	return current  // Error: Type mismatch: inferred type is String? but String was expected
}
