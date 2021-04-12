// Original bug: KT-28833

@Deprecated(message = "Deprecated", replaceWith = ReplaceWith(("String?")))
class Example(p: String)

fun exampleUsage() {
    val x = Example("") // no quick fix here
    val y: Example? = null // and here
}
