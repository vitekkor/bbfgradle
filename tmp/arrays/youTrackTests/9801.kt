// Original bug: KT-10241

open class Value

interface SerializableValue

fun foo(value: Value) {
    val serializableValue = if (value is SerializableValue) value else null
    if (serializableValue != null) {  }
}
