// Original bug: KT-27078

package test

inline class R(private val r: Int) {
    fun test() = { ok() }()

    fun ok() = println("OK")
}

fun main(args: Array<String>) {
    R(0).test()
}
