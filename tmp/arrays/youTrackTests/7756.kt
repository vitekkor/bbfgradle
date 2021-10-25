// Original bug: KT-25795

fun validateAllowedValue(value: String) {
    val allowedValues = listOf("foo", "bar", "baz")
    if (value !in allowedValues)
        throw IllegalArgumentException("$\"value\" is invalid. Allowed values are ${allowedValues.joinToString()}")
}

fun main(args: Array<String>) {
    validateAllowedValue("something invalid")
}
