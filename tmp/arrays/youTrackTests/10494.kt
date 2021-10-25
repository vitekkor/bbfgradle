// Original bug: KT-4332

fun testWhen(t: String?, x: String?): String {
    return when {
        t == null -> ""
        x == null -> t.trim() // Wrong error report here. t can be inferred as not-null. (And it actually does if you replace when with if/else if)
        else -> (t + x).trim()
    }
}
