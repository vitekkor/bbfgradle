// Original bug: KT-8596

class K {
    class Nested
}

fun foo(f: Any) {}

fun box(): String {
    K::Nested           // ok
    foo(K::Nested)      // exception :(

    return "OK"
}
