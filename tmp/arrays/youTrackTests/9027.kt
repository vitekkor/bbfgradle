// Original bug: KT-16477

data class Test(val first: String, val second: String)

fun getMapper(number: Int): (Test) -> String {
    val result = when (number) {
        1 -> Test::first
        2 -> Test::second
        else -> throw IllegalArgumentException()
    }
    return result
}

fun main(args: Array<String>) {
    val lst = listOf(
            Test("1", "2"),
            Test("one", "two")
    )

    val mapper = getMapper(1)
    lst.map { mapper(it) }
}
