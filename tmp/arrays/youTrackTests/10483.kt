// Original bug: KT-3162

fun foo(x: String) : String {
    val a = try {
        x
    } finally {
        try {
        } catch (e: Exception) {
        }
        return x
    }
}
