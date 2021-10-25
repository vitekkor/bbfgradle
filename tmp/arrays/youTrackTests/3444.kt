// Original bug: KT-38515

fun main() {
    val list = listOf(1, 2, 3)
    val prefix = "prefix:"
    val runnables = list.map {
        object : Runnable {
            override fun run() {
                println(prefix + it)
            }
        }
    }
    runnables.forEach { it.run() }
}
