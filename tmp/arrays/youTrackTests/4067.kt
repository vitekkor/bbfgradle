// Original bug: KT-31532

interface I
class A(val x: I) : I

fun main() {
    var s = mutableSetOf<I>()
    s = s.mapTo(mutableSetOf()) { (it as? A)?.x ?: it } //Error: Type inference failed
}
