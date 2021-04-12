// Original bug: KT-30760

class Inv<T>(val v: T) {}
fun case_1() {
    val a: Inv<out Number> = Inv(42)
    val b: Inv<out Nothing?> = Inv(null)
    val c = if (true) a else b
    c.v // Any?, not Number?
}
