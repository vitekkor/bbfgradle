// Original bug: KT-33108

open class A
class B : A()

class Box<X> {
    fun <Y> f1(y: Y): Box<Y> = TODO()
    fun <Y> f2(fn: (X) -> Y): Box<Y> = TODO()
    fun fz(x: X) {}
}

fun useOptional(box: Box<A>, a: A, b: B) {
    box.f1(b as A).fz(a) // No false positive.
    box.f2 { b as A }.fz(a) // False positive.
}
