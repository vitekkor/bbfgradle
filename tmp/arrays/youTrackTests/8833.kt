// Original bug: KT-17363

class A {
    companion object {
        val a = A()
        fun foo() {
            println(a)
        }
    }
    init {
        foo()
    }
}

fun main(args: Array<String>) {
    val a = A()
}
