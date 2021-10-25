// Original bug: KT-10483

interface A
class B : A {
    operator fun invoke() = this
}
fun foo(arg: A): B? {
    if (arg is B) return arg() // Unresolved reference: None of the following candidates...
    return null
}
