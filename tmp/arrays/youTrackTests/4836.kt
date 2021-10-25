// Original bug: KT-22362

infix fun String.concat(other: String) {}
fun binaryContext(anchor: String?) {
    // No problem.
    val v1 = "1234567890... add many chars ...1234567890" concat "b"
    // Problem.
    val v2 = anchor ?: "1234567890... add many chars ...1234567890" concat "b"
}
