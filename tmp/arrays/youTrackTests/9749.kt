// Original bug: KT-9586

fun f() {
    class A {
        val g: Int = error("a") // unreachable code
        val a: Int = 3 // unreachable code
    }
}

class B {
    val g: Int = error("a") //no errors
    val a: Int = 3
}
