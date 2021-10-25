// Original bug: KT-8419

data class Foo(val value: Int,val notValue: String)
data class Bar(val name: String, val age: Int)

fun main(args: Array<String>) {
    val list = listOf(Foo(1, "abc"), Foo(2,"xyz"))
    list.filter { it.value > 1 }.map {
        try {
            Bar(it.notValue, it.value)
        } catch (e: IllegalArgumentException) {
            null
        }
    }.filterNotNull()
}
