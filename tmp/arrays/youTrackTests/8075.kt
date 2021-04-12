// Original bug: KT-23350

fun main(args: Array<String>) {
    val listeners = mutableListOf<() -> Unit>()
    for (i in 1..10) {
        listeners.add { println(i) }
    }
    
    listeners.forEach { it.invoke() }
}
