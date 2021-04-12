// Original bug: KT-27137

fun main() {
    println(::a.isSuspend) // false
    println(A::b.isSuspend) // false
}

val a = suspend {
    println("1")
}

object A {
    val b = suspend {
        println("2")
    }
}
