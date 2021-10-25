// Original bug: KT-39148

fun converter(str: String) : (Double) -> Double {
    return when (str) {
        "c2f" -> { it -> it * 1.8 + 32 }
        "f2c" -> { it -> (it - 32) / 1.8 }
        else  -> return { it }
    }
}
