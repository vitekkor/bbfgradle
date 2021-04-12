// Original bug: KT-20238

package test

class C {
    val y = Test.x
}

interface Test {
    companion object {
        val x = "OK"
        val z = C().y
    }
}

fun main() {
    println(Test.z)
}
