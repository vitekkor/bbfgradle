// Original bug: KT-4240

fun main() {
	// Great! Map<String, String.(Any?)->Unit
	val map = mapOf(
		"s".to { // Undesirable dot :(
			if (it == null); // OK: Automatically declared
		}
	)
}
// Hack :(
inline fun String.to(noinline body: String.(Any?)->Unit) = Pair(this, body)
