// Original bug: KT-34841

private fun obj(f: () -> Unit) =
    object {
        fun print() {
            println("Hello")
            f.invoke()
        }
    }

fun main() {
    val obj = obj { println("Hi") }
    obj.print()
}
