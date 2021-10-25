// Original bug: KT-13367

inline fun <T,R> inlineIntoObject(crossinline f: (T) -> R) = object : java.util.function.Function<T,R> {
    override fun apply(t: T): R = f(t)
}

interface X {
    fun operation(a: String): Int
}

fun test(instance: X) {
    val f2 = inlineIntoObject(instance::operation)
}
