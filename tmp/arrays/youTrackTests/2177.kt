// Original bug: KT-24935

fun main(args: Array<String>) = println(testy())

// Typealias with generic parameter
typealias Testy<T> = Pair<T, T>

// Inline function calling constructor of typealias
inline fun testy(): Testy<String> = Testy("1", "2")

// This version compiles and executes fine
// inline fun testy(): Testy<String> = Pair("1", "2")
