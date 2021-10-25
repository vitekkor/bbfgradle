// Original bug: KT-11727

fun test(): Int? {
    val b: Int? = null
    return when (b != null) {
        true -> test2(b)
        else -> null
    }
}
fun test3(): Int? {
    val b: Int? = null
    return if(b != null)
        test2(b)
        else null
}
fun test2(b: Int) = b
