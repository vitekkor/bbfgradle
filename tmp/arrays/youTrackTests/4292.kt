// Original bug: KT-37072

fun main() {
    val i: Number = when {
        true -> 10 as Int?
        false -> 10.0 as Double?
        else -> null
    } ?: return
    println(i.toInt())
}
