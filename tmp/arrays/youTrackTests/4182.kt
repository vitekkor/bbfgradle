// Original bug: KT-4240

val map: Map<String, String.(Any?) -> Unit> = mapOf( // ERROR: Type inference failed. Expected type mismatch (mapOf)
	"s" to {
		if (it == null); // ERROR: Unresolved refernce (it) :(
	}
)
