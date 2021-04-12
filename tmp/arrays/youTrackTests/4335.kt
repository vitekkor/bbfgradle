// Original bug: KT-31601

class Optional<out T>(val value: T)

fun Any?.foo() = println("foo: $this")

fun main() {
    val a: Optional<Any?>? = Optional("a")
    val b: Optional<Any?>? = Optional(null)
    val c: Optional<Any?>? = null

    a?.let { it.value.foo() }  // Redundant `let` call could be removed;  prints "foo: a"
    b?.let { it.value.foo() }  // Redundant `let` call could be removed;  prints "foo: null"
    c?.let { it.value.foo() }  // Redundant `let` call could be removed;  prints nothing
    println("---")
    a?.value?.foo()  // after applying quick-fix;  prints "foo: a"
    b?.value?.foo()  // after applying quick-fix;  prints nothing
    c?.value?.foo()  // after applying quick-fix;  prints nothing
    println("---")
    a?.value.foo()  // alternative with leveraging `Any?` receiver;  prints "foo: a"
    b?.value.foo()  // alternative with leveraging `Any?` receiver;  prints "foo: null"
    c?.value.foo()  // alternative with leveraging `Any?` receiver;  prints "foo: null"
}
