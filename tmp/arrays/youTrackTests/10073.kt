// Original bug: KT-9711


enum class X {

    B {
        val value2 = 1
        override val value = { value2 }
    };

    abstract val value: () -> Int
}

fun main(args: Array<String>) {
    println (X.B.value())
}
