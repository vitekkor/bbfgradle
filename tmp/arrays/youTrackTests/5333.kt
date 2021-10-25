// Original bug: KT-23393

abstract class Base(
    val x: Int,
    val y: Int
)

interface IFoo

class Derived : Base, IFoo {

    constructor(
        x: Int,
        y: Int
    ) : super(
        x, // Indent level: 1
        y
    ) {
        println("Derived") // Indent level: 1
    }
}
