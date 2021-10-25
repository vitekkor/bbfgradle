// Original bug: KT-43065

inline class FieldValue(val value: String)

enum class RequestFields {
    ENUM_ONE
}

data class RequestInputParameters(
        private val backingMap: Map<RequestFields, FieldValue>
) : Map<RequestFields, FieldValue> by backingMap
