// Original bug: KT-45482

fun main() {
    val x = 1 in setOf("") // now it's a warning: Type inference failed. The value of the type parameter T should be mentioned in input types
}
