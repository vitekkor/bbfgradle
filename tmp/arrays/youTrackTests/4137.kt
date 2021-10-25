// Original bug: KT-31739

interface Foo
interface Bar

fun <T : Foo> foo(block: (T) -> Unit) {}
@JvmName("bar")
fun <T : Bar> foo(block: (T) -> Unit) {}

fun main() {
    foo<Foo> { b -> }
}
