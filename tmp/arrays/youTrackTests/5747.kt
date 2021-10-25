// Original bug: KT-18266

class RendererInKotlin(val field: String) {
    fun hold() { println() } // Breakpoint 1.
}
class RendererInJava(val field: String)
fun main(args: Array<String>) {
    val inJava = RendererInJava("in java")
    val inKotlin = RendererInKotlin("in kotlin")
    inKotlin.hold()
    println() // Breakpoint 2.
} 