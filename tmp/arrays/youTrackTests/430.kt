// Original bug: KT-41100

fun main() {
    val i = 1
    i as Int ?: println("hi") // reported: No cast needed
    i as Int? ?: println("hi") // no warning
    i as? Int ?: println("hi") // no warning
    i as? Int? ?: println("hi") // no warning
}
