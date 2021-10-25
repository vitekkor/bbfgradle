// Original bug: KT-36785

@Deprecated(message = "", replaceWith = ReplaceWith("foo()"))
object Foo: List<Int> by listOf(1)
fun foo(): List<Int> = listOf(1)

fun main() {
    println(Foo.size) // 1
    println(Foo) // 2
}
