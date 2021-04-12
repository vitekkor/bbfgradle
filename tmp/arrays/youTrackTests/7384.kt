// Original bug: KT-27557

class A {
    constructor()
    init {
        prop = "init value"
        printProp()
    }
    init {
        prop = "changed"
        printProp()
    }
    val prop: String = "and changed again"

    fun printProp() {
        println(prop)
    }
}

fun main(args: Array<String>) {
    A().printProp()
}
