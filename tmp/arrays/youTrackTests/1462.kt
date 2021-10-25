// Original bug: KT-34841

private fun array(f: () -> Unit) =
    arrayOf(1)
        .map {
            object {
                fun print() {
                    println("Hello")
                    f.invoke()
                }
            }
        }

fun main() {
    val array = array { println("Hi") }
    array[0].print()
}
