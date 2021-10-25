// Original bug: KT-9532

object A {
    const val z = "$"
    const val b = "1234$z"
}

object B {
    const val z = "$"
    const val b = "1234$z"
}

fun main(args: Array<String>) {
    print(A.b === B.b); //false
}
