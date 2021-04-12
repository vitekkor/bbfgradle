// Original bug: KT-40627

fun main() {
    val a = """
        foo  
    """.trimIndent()

    val b = """
        foo
    """.trimIndent()

    println(if (a==b) "equal" else "different")
}
