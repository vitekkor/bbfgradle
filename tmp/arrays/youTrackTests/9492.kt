// Original bug: KT-10002

fun baz1(s: String?): String {
    if (s == null) return ""
    // if explicit type String is given for t, problem disappears
    val t = when(s) {
        // if removed, type mismatch appears at return
        "abc" -> s!!
        else -> "xyz"
    }
    return t
}

fun baz2(s: String?): String {
    // If String type is given explicitly, problem disappears
    val t = if (s == null) {
        ""
    }
    else {
        val u: String? = null
        if (u == null) return ""
        // If removed, type mismatch appears in return
        u!!
    }
    return t
}
