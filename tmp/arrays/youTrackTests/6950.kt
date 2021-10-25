// Original bug: KT-22100

object Foo {
    fun `function with spaces`(): String {
        data class Bar (val s: String)
        return Bar("xyz").toString()
    }
}

fun main(args: Array<String>) {
    println(Foo.`function with spaces`())
}
