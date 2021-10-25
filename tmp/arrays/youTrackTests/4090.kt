// Original bug: KT-32686

sealed class Base<T> {
    class A<T> : Base<T>()
    class B<T> : Base<T>()
}

fun getBase(num: Int) = when (num) {
    1 -> Base.A()
    else -> Base.B<Int>()
}
