// Original bug: KT-23742

package co


@Suppress("UNSUPPORTED_FEATURE")
inline class Wrapper(val internal: Int)


inline fun <T> foo(callback: () -> T): T {
    return callback()
}

inline fun bar(callback: () -> Wrapper): Wrapper {
    return callback()
}

fun main(args: Array<String>) {
    println(foo { Wrapper(10) }.internal) // Boxing
    println(bar { Wrapper(10) }.internal) // Boxing
}

