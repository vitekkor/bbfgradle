// Original bug: KT-24553

data class Text(
	val text: String
)
fun main(args: Array<String>) {
    val text = Text("Hello from Kotlin")
    println(text.text)
    println(text.hashCode())
}
