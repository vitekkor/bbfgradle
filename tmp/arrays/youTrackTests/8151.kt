// Original bug: KT-22771

class SomeClass {
    class NestedClass {
        val valInNestedC = 42
    }
}

fun main(args: Array<String>) {
    val test = SomeClass.NestedClass().valInNestedC
}
