// Original bug: KT-2585

fun foo() {
    try {
        throw RuntimeException()
    } catch (e: Exception) {
        return     // <- Wrong UNREACHABLE_CODE
    } finally {
        while (true);
    }
}
