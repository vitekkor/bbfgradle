// Original bug: KT-42478

class Bar<R>(var x: R)

fun <K> take(x: K, y: Bar<K>) {}

class A
class B

fun main(x: A, y: B) {
    take(x, Bar(y)) // `Bar(y)` is inferred to `Bar<Any>`
}
