// Original bug: KT-21616

package test

class Top(val x: String) {
    open class In1(val f: () -> String)

    inner class In2 : In1({ x })

    fun test() = In2().f()
}

fun main(args: Array<String>) {
    Top("OK").test()
}
