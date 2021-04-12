// Original bug: KT-39148

fun converter(str: String) : (Double) -> Double {
    return when (str) {
        "c2f" -> { f -> f * 1.8 + 32 }
        "f2c" -> { c -> (c - 32) / 1.8 }
        else  -> { x -> x }
    }
}
