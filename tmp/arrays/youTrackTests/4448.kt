// Original bug: KT-17305

fun <T : CharSequence?> test(s: T): Any? {
    if (s == null) return null
    val map: MutableMap<CharSequence, Any> = mutableMapOf()
    return map.get(s) // here
}
