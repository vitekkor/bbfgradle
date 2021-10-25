// Original bug: KT-35134

sealed class A(val x: Int) {
    object B : A(1)
}

fun main() {
    val a: Any = A.B

    if (a is A) {
        when (a) {
            a.x == 1 -> print("1")
        }
    }
}
