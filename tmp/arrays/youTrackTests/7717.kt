// Original bug: KT-24900

interface Configuration {
    fun <T : Any> get(key: String, converter: Converter<T>, default: () -> T): T
    fun <T : Any> get(key: String, converter: Converter<T>, default: T): T = get(key, converter, { default })
}

interface Converter<out T> {
    fun convert(s: String): T
}
