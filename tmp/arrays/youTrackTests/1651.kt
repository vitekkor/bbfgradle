// Original bug: KT-25345

fun <T> deserialize(s: String): T {
    val i = s.toInt()
    @Suppress("UNCHECKED_CAST")
    return i as T
}

inline fun <reified T : Any> deser(s: String): T? = when(s) {
    "foo" -> null
    else -> deserialize(s)
}

fun main() {
    val i: Int? = deser("5");
    println(i)
}
