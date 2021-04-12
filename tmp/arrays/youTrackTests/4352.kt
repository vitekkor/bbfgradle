// Original bug: KT-36915

sealed class Parent(open val ab: String)
sealed class Child1(override val ab: String) : Parent(ab)
sealed class Child2(override val ab: String) : Child1(ab)
sealed class Child3(override val ab: String) : Child2(ab)
sealed class Child4(override val ab: String) : Child3(ab)
class Child5(override val ab: String) : Child4(ab)

fun main() {
    val child = Child5("Hello")
    println("child = $child")
}
