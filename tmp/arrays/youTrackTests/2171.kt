// Original bug: KT-18465

object O {
    const val a = 1
    val b = foo()
    fun foo() : Int {
        println(a)
        return 2
    }
}

object P {
    val b = foo() // in this object declaration of a and b are reversed
    const val a = 1
    fun foo() : Int {
        println(a)
        return 2
    }
}

fun main(args: Array<String>) {
    println(O.b)
    println(P.b)
}
