// Original bug: KT-17377

interface B {
    fun C() {}
}

class A : B {
    inner class C {}
}

fun main(args: Array<String>) {
    println(A())
    //println(A().C()) // <- C() is ambiguous
}
