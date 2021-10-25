// Original bug: KT-32425

fun `test type inference bound callable reference`() {
    val strings = listOf("One", "Two", "Three")
    val stringBuilder = StringBuilder()
    strings.forEach(stringBuilder::append)
}
