// Original bug: KT-30571

class In<in K>

fun <K> create(x: In<K>, y: In<K>): Delegate<K> = TODO()

class Delegate<T> {
    operator fun getValue(thisRef: Any?, kp: Any?): T = TODO()
}

class A
class B

val foo by create(In<A>(), In<B>())
