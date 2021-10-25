// Original bug: KT-10637

abstract class A {
    init {
        println("A")
    }
}

class B : A() {
    init {
        println("B")
    }
}

fun main(args: Array<String>) {
    B()   // prints "A" and "B"
}
