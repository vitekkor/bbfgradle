// Original bug: KT-23141

class Inv<T>

fun <S1, S2> select(a: Inv<S1>, b: Inv<S2>) {}
fun <T> select(a: Inv<T>, other: T) {}

fun test() {
    select(Inv<Int?>(), null) // ambiguity
}
