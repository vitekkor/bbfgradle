// Original bug: KT-6863

private fun interpret(line: String) {
    val pipeAction: (String, () -> Unit) -> () -> Unit = { a, b -> {} }
    val lastActions = listOf<String>().foldRight({}, pipeAction)
}
