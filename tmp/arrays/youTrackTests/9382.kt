// Original bug: KT-14102

enum class TestEnum {
    ONE, TWO
}

fun test(x: TestEnum) {
    val xx: Int
    when (x) {
        TestEnum.ONE -> xx = 1
        TestEnum.TWO -> xx = 2
        // implicit exhaustive 'when'
    }
    println(xx)
}
