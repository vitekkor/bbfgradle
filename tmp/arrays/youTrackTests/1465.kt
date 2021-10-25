// Original bug: KT-34841

private fun array(f: () -> Unit) =
    arrayOf(1)
        .map {
            val foo = object : Foo {
                override fun print() {
                    println("Hello")
                    f.invoke()
                }
            }
            foo
        }

fun main() {
    val array = array { println("Hi") }
    val foo: Foo = array[0]
    foo.print()
}

interface Foo {
    fun print()
}
