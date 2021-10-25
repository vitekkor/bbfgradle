// Original bug: KT-10003

fun baz(s: String?): String {
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
