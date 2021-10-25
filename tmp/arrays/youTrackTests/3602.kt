// Original bug: KT-28833

@Deprecated(message = "Too many letters in this type alias", replaceWith = ReplaceWith("Int?"))
typealias IntOrNull = Int?

fun usage() {
    val x: IntOrNull = null // no quick fix here
}
