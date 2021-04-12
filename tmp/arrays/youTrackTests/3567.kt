// Original bug: KT-38383

fun main() {
    MyTokens.foo
}

internal object MyTokens {
    val foo = Token.FOO // this line causes function inlining in Token.Companion works improperly
}

internal sealed class Token(val value: Char) {
    object FOO : Token('}')

    companion object {
        init {
            val all = arrayOf(
                FOO
            )

            all.map { it.toString() }
        }
    }
}
