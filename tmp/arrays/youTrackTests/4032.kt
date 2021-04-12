// Original bug: KT-15155

class Value<T>(val value: T)

class LimitedValue<S : Value<T>, T : Number>(val limited: S)

fun foo(o: LimitedValue<*, *>) {
    (o.limited as Value<*>).value
}
