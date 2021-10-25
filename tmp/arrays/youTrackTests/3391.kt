// Original bug: KT-20238

package test

interface Test {
    companion object {
        val x = "OK"

        class C {
            val y = x
        }

        val z = C().y
    }
}

fun box() = Test.z

fun main(args: Array<String>) {
    println(box())
}
