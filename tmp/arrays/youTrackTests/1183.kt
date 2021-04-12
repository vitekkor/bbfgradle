// Original bug: KT-44861

fun main() {
    println(Foo())
}
sealed class Foo() {
    class A : Foo()
    class B : Foo()
}
fun Foo(kind: String = "A"): Foo = when (kind) {
    "A" -> Foo.A()
    "B" -> Foo.B()
    else -> error("unknown")
}
