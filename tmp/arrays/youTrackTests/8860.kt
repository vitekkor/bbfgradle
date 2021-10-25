// Original bug: KT-18272

open class Outer {
    protected open val b = 2
    private val m = b

    fun printM() {
        println(m)
    }
}

class Subclass : Outer() {
    override val b = 5   // 'b' is protected
}

fun main(args: Array<String>) {
    val outer = Outer()
    outer.printM()

    val sub = Subclass()
    sub.printM()
}
