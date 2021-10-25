// Original bug: KT-16732

fun valueFromDB(value: Any): Any {
    return when (value) {
        is Char -> value
        is Number-> value.toChar()
        is String -> value.single()
        else -> error("Unexpected value of type Char: $value")
    }
}
