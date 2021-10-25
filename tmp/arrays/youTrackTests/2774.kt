// Original bug: KT-40496

interface Serializer<T>

@Deprecated("", replaceWith = ReplaceWith("deserialize(s)"))
fun <T> parse(s: Serializer<T>) {}

fun <T> deserialize(s: Serializer<T>) {}

fun main() {
    val serializer: Serializer<out Any?> = TODO()
    parse(serializer)
}
