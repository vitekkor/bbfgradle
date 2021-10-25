// Original bug: KT-38644

sealed class Test
data class A(val i: Int): Test()
data class B(val j: Int): Test()

data class Wrap<T: Test>(val t: T)

fun main() {
    val list = listOf(Wrap(A(1)), Wrap(A(2)), Wrap(B(3)))
    println(list)
    println(list.filterIsInstance<Wrap<B>>()) // should be [Wrap(t=B(j=3))], but is the entire list
}
