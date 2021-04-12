// Original bug: KT-35441

data class DataClass(val face: String)

enum class ENUM(
    private val checker: List<DataClass>.() -> Boolean
) {

    A({
        asSequence()
            .groupBy { it.face }
            .map { it.value }
            .map { it.size }
            .any { it >= 5 }
    });
    fun check(dataClasses: List<DataClass>) = checker(dataClasses)
}

fun main() {
    ENUM.A.check(listOf(DataClass("A")))
}
