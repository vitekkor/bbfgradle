// Original bug: KT-31630

class A
class B

fun toB(a: A) = B()

fun List<A>.toB(): List<B> = this.map(::toB)

fun <T> List<T>.map(transform: (T) -> B): List<B> {
    return null!!
}
