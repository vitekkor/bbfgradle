// Original bug: KT-24900

interface Converter<out V>

interface Configuration {
    fun <T> get(converter: Converter<T>, default: () -> T) // (1)
    fun <S> get(converter: Converter<S>, default: S) // (2)

    fun <K> foo(c: Converter<K>, d: K) {
        get(c) { d }
    }
}
