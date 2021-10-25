// Original bug: KT-7391

class A<T>(public var a: T)
class B<T>(public var b: T)
class C<T>

fun foo2(p: A<B<out C<*>>>): A<B<out C<*>>> = p

fun main(args: Array<String>) {
    val p : A<B<out C<*>>> = A(B(C<Any>()))
    val x2: A<B<*>> = foo2(p) as A<B<*>>
    x2.a = B(1)
    val z = p.a.b
}
