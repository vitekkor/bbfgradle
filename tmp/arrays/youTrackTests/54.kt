// Original bug: KT-45630

class EnumSerializer<T : Enum<T>>(val values: Array<T>)

private fun <T : Any> Class<T>.createEnumSerializer(): Any {
    return EnumSerializer(enumConstants as Array<out Enum<*>>)
}
