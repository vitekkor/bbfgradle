// Original bug: KT-19406

package test

open class Base {
    @JvmField val x = "O"
}

class Host: Base() {
    fun foo() = bar(x)

    companion object {
        val x = "K"

        fun bar(s: String) = s + x
    }
}

fun main(args: Array<String>) {
    println(Host().foo())
}
