// Original bug: KT-10364

interface Mod<out T, out F>
interface Scalar<V>

interface B
class TestField

val <F> Mod<TestField, F>.field1: Mod<Scalar<Int>, F> get() = null!!

operator fun <T, BB: B> Mod<T, BB>.invoke(b: Mod<T, BB>.() -> Unit) {}
operator fun <T : Any, BB: B> Mod<Scalar<T>, BB>.invoke(t: T) { }

fun Mod<TestField, B>.test() {
    field1(1) // cannot chose between invokes
   (field1)(1) // ok
}
