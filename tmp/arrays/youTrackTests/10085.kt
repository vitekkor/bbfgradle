// Original bug: KT-9657

abstract class Your {
    abstract val your: String

    fun foo() = your
}

class A {
    val my: String = "O"
        get() = object : Your() {
            override val your = field
        }.foo() + "K"
}

fun box() = A().my

fun main(args: Array<String>) {
    box()
}
