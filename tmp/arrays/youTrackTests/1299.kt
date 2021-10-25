// Original bug: KT-35669

fun main() {
    foo(bar()) // put breakpoint here, select `foo` at Smart Step Into, it will step to `bar`
}

fun foo(a: Any) {
    println("foo")
}

inline fun bar(): Any = Any()
