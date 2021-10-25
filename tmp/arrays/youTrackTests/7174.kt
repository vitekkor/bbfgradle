// Original bug: KT-23639

sealed class A<T> {
    class B<T> : A<T>()
    class C<T>(val d : T) : A<T>()
}

fun <T> A<T>.dOrNull() : T? {
    return when (this) {
        is A.B -> null
        is A.C -> d
    }
}
