// Original bug: KT-42526

interface A
interface B : A

open class C<T : Any, R: Any> {
    open fun foo(t: T): Unit = println("C.foo " + t::class.simpleName)
    open fun bar(t: R): Unit = println("C.bar " + t::class.simpleName)
    fun test1(t: @UnsafeVariance T, r: @UnsafeVariance R) {
        try {
            foo(t)
        } catch (e: Throwable) {
            println("caught from foo")
        }
        try {
            bar(r)
        } catch (e: Throwable) {
            println("caught from bar")
        }
    }
    fun test2(t: Any, r: Any) {
        try {
            foo(t as T)
        } catch (e: Throwable) {
            println("caught from foo")
        }
        try {
            bar(r as R)
        } catch (e: Throwable) {
            println("caught from bar")
        }
    }
}

class D<T : A> : C<T, A>() {
    override fun foo(t: T): Unit = println("D.foo " + t::class.simpleName)
    override fun bar(t: A): Unit = println("D.bar " + t::class.simpleName)
}

fun main() {
    val d = D<A>()
    val c: C<out Any, out Any> = d
    c.test1(Any(), Any())
    c.test2(Any(), Any())
}
