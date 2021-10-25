// Original bug: KT-45960

sealed class A {

    object B : A()
    class C : A()
}

fun main() {
    val b: A = A.B

    when {  // 1
        b === A.B -> println("b")
        b is A.C -> println("c")
    }

    when (b) {  // 2
        A.B -> println("b")
        is A.C -> println("c")
    }

    when (b) {  // 3
        is A.B -> println("b")
        is A.C -> println("c")
    }
}
