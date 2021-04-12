// Original bug: KT-40221

data class Foo(
        val field1: String,
        val field2: Boolean,
        val field3: Boolean
)

fun test() {
    val foo1 = Foo("1", false, true)
    val foo2 = Foo("2", true, true)
    val foo3 = Foo("3", true, true)
}
