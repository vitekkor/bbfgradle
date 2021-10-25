// Original bug: KT-22043

enum class Foo : MyInterface { X, Y }
interface MyInterface

fun test(i: MyInterface, a: Any) {
    Foo.X == Foo.Y // OK
    Foo.X == i // OK
    Foo.X == a // OK
}
