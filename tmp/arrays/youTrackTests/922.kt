// Original bug: KT-45342

class Foo(val a: String, val b: String)

val z = if (true) {
    val x: ((Foo) -> String) = when (true) {
        true -> Foo::a
        else -> Foo::b
    }
    Unit
} else {
    Unit
}
