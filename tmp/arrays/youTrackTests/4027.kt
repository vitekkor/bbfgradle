// Original bug: KT-14803

class A {
    val a by lazy {
        val x = f<Int>() ?: throw IllegalStateException("Lol")

        x
    }
}

fun <T> f(): T? = null

fun h(x: Int) {}

fun main(args: Array<String>) {
    h(A().a)
}
