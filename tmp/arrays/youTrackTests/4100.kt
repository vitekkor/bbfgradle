// Original bug: KT-30240

interface A
interface B<T>
class C<T>

fun <T, K> foo(c: C<T>)
        where K : A,
              K : B<T> {
}

fun usage(c: C<Any>) {
    foo(c) // TODO should compile
}
