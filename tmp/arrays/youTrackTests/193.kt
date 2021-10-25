// Original bug: KT-45762

class A<T>(
    val value: T
)

class B<T>(
    val value: T,
    val a: A<T>
)

fun Any.copy(): Any? = when(this) {
    is B<*> -> copy2()
    else -> null
}

fun <T> B<T>.copy2() = B(value, a)
