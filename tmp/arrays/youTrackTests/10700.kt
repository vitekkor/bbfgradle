// Original bug: KT-2229

fun a(f:(Int)->Unit) {}

fun f():Int = 1

fun main(args : Array<String>) {
    a { i -> f() } // <- Type mismatch: inferred type is (jet.Int) -> jet.Int but (jet.Int) -> Unit was expected
}
