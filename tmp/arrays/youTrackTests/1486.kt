// Original bug: KT-21662

fun test() {
    Foo.print()
    Bar.print()
}

class Foo {
    companion object {
        inline fun print() {
            println("Foo:print()")
        }
    }
}

object Bar {
    inline fun print() {
        println("Bar:print()")
    }
}
