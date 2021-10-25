// Original bug: KT-45738

fun main() {
    val v1 = parse("v1")
    val v2 = parse("value1")
    val v3 = parse("Value1")
    println(v1)
    println(v2)
    println(v3)
}

private fun parse(text: String) = when (text) {
    AnyEnum.Value1.name, "v1", "value1" -> AnyEnum.Value1
    AnyEnum.Value2.name, "v2", "value2" -> AnyEnum.Value2
    else                                -> "FAIL"
}

enum class AnyEnum {
    Value1,
    Value2,
}
