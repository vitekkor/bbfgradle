// Original bug: KT-20808

interface IBar

abstract class X(val y: IBar)

object Bar: IBar {
    val prop = Foo.CONST
}

class Foo {
    companion object: X(Bar) {
        val CONST = "AAA"
    }
}

fun main(args: Array<String>) {
    Bar.prop
    Foo.CONST
}
