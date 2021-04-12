// Original bug: KT-30792

inline fun <reified EnumType : Enum<EnumType>> enumValueOfOrNull(name: String?): EnumType? =
        name?.runCatching { enumValueOf<EnumType>(this) }?.getOrNull()

inline fun <reified EnumType : Enum<*>> enumValueOrDefault(name: String?, defaultValue: () -> EnumType): EnumType =
        enumValueOfOrNull<EnumType>(name) ?: defaultValue.invoke()
