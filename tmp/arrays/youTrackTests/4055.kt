// Original bug: KT-18014

fun main() {
    val lines : List<String> = listOf("")
    lines.mapNotNull { it.toStepicResult() }.sortedWith(Comparator.comparing { it.Q7 })
}

private val String.Q7: String
    get() = ""

fun String.toStepicResult() = ""
