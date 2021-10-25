// Original bug: KT-44776

interface Foo

fun <T: Foo> takeBar(bar: T) {}

fun <K> materialize(): K = TODO()

fun main() {
    takeBar(materialize()) // K and T are still successfully inferred into Foo
}
