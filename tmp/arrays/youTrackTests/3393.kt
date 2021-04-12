// Original bug: KT-20238

package test

interface Test {
    companion object {
        val x = "OK"

        enum class NestedEnum {
            ENTRY {
                override val y = x
            };
            abstract val y: String
        }

        val z = NestedEnum.ENTRY.y
    }
}

fun box() = Test.z

fun main(args: Array<String>) {
    println(box())
}
