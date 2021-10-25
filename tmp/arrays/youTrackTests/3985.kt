// Original bug: KT-19880

class NullableBool(var value: Boolean?)

fun foo(b: Boolean) = println("foo($b: Boolean)")
fun foo(b: Any?) = println("foo($b: Any?")

fun main(args: Array<String>) {
    val r = NullableBool(false)

    foo(r.value) // OK
    r.value = true
    foo(r.value) // Error:(10, 9) Kotlin: Smart cast to 'Boolean' is impossible <...>
}
