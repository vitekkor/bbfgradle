// Original bug: KT-22538

open class Foo
object Bar : Foo() {
    val test: String = ""
    fun fooObj(): Int = 42
}

fun testFunction(foo: Foo) {
    when (foo) {
        is Bar -> foo.fooObj() // `is` removal will lead to the broken reference
        else -> {}
    }
}
