// Original bug: KT-43020

class Test {
    val a = 1
    fun foo() {
        val a = 1 // no warning
        println(a)
    }
}
val a = 1
fun outer(a: String) { // no warning
    fun inner(a: String) { // no warning
        println(a)
    }
}
