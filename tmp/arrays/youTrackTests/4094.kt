// Original bug: KT-32802

fun blah(thing: String?): String = when (thing) {
    null -> "hello"
    else -> try {
        "goodbye"
    } catch (e: Exception) {
        thing
    }
}
