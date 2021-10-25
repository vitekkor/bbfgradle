// Original bug: KT-23675

class Environment(
    val fieldAccessedInsideChild: Int,
    val how: Environment.() -> Unit
)

fun main(args: Array<String>) {
    Environment(
        3,
        {
            class Child {
                val a = fieldAccessedInsideChild
            }

            class Parent {
                val children: List<Child> =
                    (0..4).map { Child() }
            }
        }
    )
}

