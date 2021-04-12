// Original bug: KT-30297

class Inv<T>(val x: T?)

fun <K> create(y: K) = Inv(y) // wrong approximation of Inv<K!!> ?

fun takeInvInt(i: Inv<Int>) {}

fun <S> test(i: Int, s: S) {
    Inv(s) //  type is Inv<S!!>, it's fine
    create(i) // type of create(i) is Inv<out Int>
    takeInvInt(create(i)) // as a result we get a type mismatch, note that there is no error in old inference
}
