// Original bug: KT-28621

sealed class A<T, S> {
    class B<T> : A<T, Int>()
    class C<T>(val d : T) : A<T, Double>()
}
fun <T> A<T, Double>.dOrNull() : T? {
    return when (this) {
        is A.B -> null
        is A.C -> d
    }
}
