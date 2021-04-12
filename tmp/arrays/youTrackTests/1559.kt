// Original bug: KT-5837

fun <T> List<T>.g(): Int = size

val <T> List<T>.foo: Int by List<T>::g

inline operator fun <T, R> (T.() -> R).getValue(instance: T, prop: Any?): R = instance.this()
