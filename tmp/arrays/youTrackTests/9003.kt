// Original bug: KT-17329

fun <T: Any, TSub: T> cast(clazz : Class<TSub>, value: T) = clazz.cast(value)
val n : Number = 1
val i : Int = cast(Int::class.java, n) // should not throw a ClassCastException
