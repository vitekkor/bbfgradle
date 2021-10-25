// Original bug: KT-43162

class Foo {
    fun bar() = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyza".replace(
        "aaaa",
        "bbbb"
    )
    fun baz() = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcde".replace("aaaa".toRegex()) { it.value.repeat(
        2
    )    }
}
