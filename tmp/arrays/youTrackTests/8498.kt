// Original bug: KT-7257

package lv.n3o.test

enum class X {
    A {
        override val value = 1
    },

    B {
        val value2 = 1
        override val value = 1.let { it + value2 }
    };

    abstract val value: Int
}

fun main(args: Array<String>) {
    println (X.A.value)
}
