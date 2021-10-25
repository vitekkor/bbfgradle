// Original bug: KT-1600

abstract class Foo<T> {
    fun hello(id: T) {
        println("Hi $id")
    }
}

class Bar: Foo<String>() {
}

fun main(args : Array<String>) {
    Bar().hello("Reg")
}
