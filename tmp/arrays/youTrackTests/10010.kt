// Original bug: KT-9601

open class A {
    fun foo(a: Any) {}
    fun fas(a: Int) {}
}
class B: A() {
    fun foo(a: Int) {}
    fun fas(a: Any) {}
}

fun test() {
    B::foo // which foo & fas?
    B::fas
}
