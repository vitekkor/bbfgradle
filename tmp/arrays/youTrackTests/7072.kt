// Original bug: KT-26336

enum class TarEnum {
    VAL
}
inline fun <reified T : Enum<T>> String?.enumOrNull(): T? {
    this ?: return null
    return enumValues<T>().firstOrNull { it.name == this }
}

inline fun <reified T : Enum<T>> String?.enumOrThrow(handleNull: () -> Throwable = { IllegalArgumentException("Enum type ${T::class.java} not contain value=$this") }): T {
    return this.enumOrNull<T>() ?: throw handleNull()
}
