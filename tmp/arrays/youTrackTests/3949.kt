// Original bug: KT-37650

class Inv<T>

fun <T> materialize() = null as T

fun <A> foo(x: Inv<A>) {}
fun <B> bar(y: Inv<out B>): Inv<Inv<out B>> = materialize()

fun <K> test(plant: Inv<out K>) {
    val x = foo(bar(plant)) // OK in OI, NI: "Not enough information to infer type variable A"
}
