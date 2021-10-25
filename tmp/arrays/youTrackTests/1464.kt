// Original bug: KT-34841

private fun obj(f: () -> Unit) =
    object {
        fun print() {
            println("Hello")
            f.invoke()
        }
    }

private fun array(f: () -> Unit) =
    arrayOf(1).map { obj(f) }

fun main() {
    val array = array { println("Hi") }
    array[0].print()
}
