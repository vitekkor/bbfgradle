// Original bug: KT-37352

package foo

enum class Choice {
    ONE, TWO, THREE
}

interface Foo {
    val choice1: Choice
    val choice2: Choice
    val choice3: Choice
}

class BarFoo(override val choice1: Choice, override val choice2: Choice, override val choice3: Choice) : Foo {

    companion object {
        val default = BarFoo(Choice.ONE, Choice.TWO, Choice.THREE)
    }
}
