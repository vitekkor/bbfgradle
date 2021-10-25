// Original bug: KT-17363

class A {
    companion object {
        val a = A()
        const val b = 2
        fun foo() {
            println(b)
        }
    }
    init {
        foo()
    }
}

fun main(args: Array<String>) {
    val a = A()
}
