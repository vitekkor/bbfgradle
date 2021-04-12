// Original bug: KT-13950

class A<T>
class B<T>

fun <E> foo(b: B<in A<E>>) {}

fun bar(b: B<in A<out Number>>) {
    foo(b) // Error:(7, 5) Kotlin: Type inference failed: 'E' cannot capture 'out Number'. Only top-level type projections can be captured
}
