// Original bug: KT-20876

fun foo(bar: Any) {
    println("Without default")
}

fun foo(bar: Any, baz: Any = "Default") {
    println("With default")
}

fun main(args: Array<String>) {
    foo(123)
}
