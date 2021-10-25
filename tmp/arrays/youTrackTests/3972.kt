// Original bug: KT-13965

class Wrapper<T>(val value: T) {
    operator fun invoke(other: T) { }
}

val <T> T.foo: Wrapper<T> get() = Wrapper(this)

fun main(args: Array<String>) {
    "abc".foo("abc")
}
