// Original bug: KT-32425

fun `test type inference lambda`() {
    val strings = listOf("One", "Two", "Three")
    val stringBuilder = StringBuilder()
    strings.forEach { stringBuilder.append(it) }
}
