// Original bug: KT-31585

inline class FieldValue(val value: String)

enum class RequestFields {
    ENUM_ONE
}

private data class RequestInputParameters(
    private val backingMap: Map<RequestFields, FieldValue>
) : Map<RequestFields, FieldValue> by backingMap

fun main() {
    val testMap = mapOf(RequestFields.ENUM_ONE to FieldValue("value1"))
    val test = testMap[RequestFields.ENUM_ONE] // works!
    println(test)

    val testMap2 = RequestInputParameters(mapOf(RequestFields.ENUM_ONE to FieldValue("value1")))
    val test2 = testMap2[RequestFields.ENUM_ONE] // does not work :-(
    println(test2)
}
