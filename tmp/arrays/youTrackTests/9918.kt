// Original bug: KT-10175

open class Base
class Derived : Base() {
    fun bar() {}
}

fun foo(b: Base?) {
    if (b is Derived?) {
        // b: smart cast to <?>
        b?.bar()
    }
}
