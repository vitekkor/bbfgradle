// Original bug: KT-2585

fun foo(x: String): String {
    try {
        throw RuntimeException()
    } finally {
        try {
        } catch (e: Exception) {
        }
        return x     // <- Wrong UNREACHABLE_CODE
    }
}
