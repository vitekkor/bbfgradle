// Original bug: KT-41427

val foo = "foo"
fun bar() = "bar"

class Foo : () -> String by ::foo
class Bar : () -> String by ::bar

fun main(args: Array<String>) {
    println(Foo()())
    println(Bar()())
}
