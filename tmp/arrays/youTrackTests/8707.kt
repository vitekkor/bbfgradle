// Original bug: KT-7579

object SwitchOverConstants {
    const val foo = 1
    const val bar = 2
    const val baz = 3

    fun one(x: Int) = when (x) {
        foo -> "foo"
        bar -> "bar"
        baz -> "baz"
        else -> "else"
    }

    fun two(x: Int) = when (x) {
        SwitchOverConstants.foo -> "foo"
        SwitchOverConstants.bar -> "bar"
        SwitchOverConstants.baz -> "baz"
        else -> "else"
    }
}
