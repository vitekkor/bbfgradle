// Original bug: KT-25268

fun case_1(value: Int): Number {
    val whenValue = when (value) {
        1 -> 1L
        2 -> 10
        else -> 10.toShort()
    }

    return whenValue
}
