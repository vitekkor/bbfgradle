// Original bug: KT-18247

class ClassLevel(private val field: String) {
    fun speak() = field

    fun instanceContext() {
        println("Just an instance context.") // Breakpoint 2.
    }
}

fun main(args: Array<String>) {
    val watcher1 = ClassLevel("a")
    watcher1.instanceContext() // Breakpoint 1.
    println("Hold.")
} 