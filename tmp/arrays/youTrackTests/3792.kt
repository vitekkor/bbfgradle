// Original bug: KT-7664

data class A(val x: Int)

fun isAny(x: Any?) {
    if (x is Any) {
        println("$x is Any")
    } else {
        println("$x is not Any")
    }
}

fun main(args: Array<String>) {
    isAny(A(0))
    isAny(1)
    isAny("ABC")
}
