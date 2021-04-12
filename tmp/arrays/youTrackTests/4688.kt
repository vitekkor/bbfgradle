// Original bug: KT-30244

fun decodeValue(value: String) =
        when (value) {
            "F" -> String::toFloat
            "B" -> String::toBoolean
            else -> TODO()
        }
