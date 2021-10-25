// Original bug: KT-30300

class Inv<T>
class InvOut<T, out K>

class Sample

fun <K> select(x: K, y: K): K = x
fun <K, V> selectInvOut(a: InvOut<out K, V>, b: InvOut<out K, V>): InvOut<K, V> = TODO()
fun <K, V> emptyInvOut(): InvOut<K, V> = TODO()
fun <S> create(element: S): InvOut<Inv<S>, S> = TODO()

fun test(s: Sample, b: InvOut<Inv<*>, Any?>) {
    selectInvOut(b, select(create(s), emptyInvOut())) // error, type mismatch
}
