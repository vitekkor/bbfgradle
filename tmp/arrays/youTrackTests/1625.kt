// Original bug: KT-25738

sealed class S {
    object O : S()

    companion object {
        val x = foo(O)
    }
}

fun foo(o: S): Int = 42

fun main(args: Array<String>) {
    S.O
}
