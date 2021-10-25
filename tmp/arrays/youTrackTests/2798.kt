// Original bug: KT-39123

sealed class Sealed {
    class A : Sealed()
    class Foo : Sealed()
}

fun main() {
    val sealed: Sealed = Sealed.Foo()
    when (sealed) {
        is Sealed.Foo -> println("Foo")
        is Sealed.A -> println("A")
    }
}
