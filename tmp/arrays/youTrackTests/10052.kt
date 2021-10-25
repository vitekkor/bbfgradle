// Original bug: KT-3879

open class A(bar: Int = 5) {
    init {
        println("A: " + bar) //print A: 5
    }
}

class B(bar: Int = 2) : A() {
    init {
        println("B: " + bar) // print B: 2
    }
}

fun main(args: Array<String>) {
    B()
}
