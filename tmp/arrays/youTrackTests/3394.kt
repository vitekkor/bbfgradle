// Original bug: KT-20238

package test

interface Test {
    companion object {
        val x = "OK"

        interface NestedInterface {
            fun foo(s: String = x): String
        }

        class NestedClass : NestedInterface {
            override fun foo(s: String) = s
        }

        val z = NestedClass().foo()
    }
}

fun box() = Test.z

fun main(args: Array<String>) {
    println(box())
}
