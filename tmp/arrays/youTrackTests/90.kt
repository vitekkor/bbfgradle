// Original bug: KT-17691

fun foo(vararg foo: Any, bar: Any) {}

fun main() {
    var x = 1
    foo(foo = *arrayOf({ x = 2 }()), bar = { x = 3 }())

    if (x == 3) {
        throw Exception() // will be thrown only after the change
    }
}
