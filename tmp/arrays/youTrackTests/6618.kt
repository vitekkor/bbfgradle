// Original bug: KT-29922

sealed class Super

class Child_1: Super()

class Child_2: Super()


fun main() {
    val superVal: Super = Child_1()

    // using "when" as an expression to make sure the "when" is exhaustive
    @Suppress("UNUSED_VARIABLE")
    val ignore: Unit = when (superVal) {
        is Child_1 -> {
            println("child 1")

            Unit
        }

        is Child_2 -> {
            println("child 2")

            Unit
        }
    }
}
