// Original bug: KT-25721

fun doSomething(check: Boolean): List<String> {
    return if (check) {
        emptyList()
    } else {
        try {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
