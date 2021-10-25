// Original bug: KT-31774

interface I1
interface I2

fun <T> foo(x: Any) where T : I1, T : I2 {
    x as T // Should be checked for I1 and I2, but there's only CHECKCAST I1
}

class W : I1, I2

fun main() {
    foo<W>(object : I1 {}) // CCE should happen, but it doesn't
}
