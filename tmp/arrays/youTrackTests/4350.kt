// Original bug: KT-33119

inline class WrappingInt(val value: Int) {
    operator fun inc(): WrappingInt = plus(1)
    operator fun plus(num: Int): WrappingInt = WrappingInt((value + num) and 0xFFFF)
}

fun main() {
    var x = WrappingInt(65535)
    x++
    println(x) // prints 0 (correct)

    var y = WrappingInt(65535)
    ++y
    println(y) // prints 65536
}
