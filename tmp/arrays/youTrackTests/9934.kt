// Original bug: KT-3629

class Empty<T>

class Identity<T>(val value: T) {
    companion object {
        infix fun <A> wrap(a : A): Identity<A> = Identity(a)
    }
}

fun dummy(): Identity<Empty<Int>> = Identity.wrap(Empty()) // ok
fun dummy2(): Identity<Empty<Int>> = Identity wrap Empty() // type inference failed
