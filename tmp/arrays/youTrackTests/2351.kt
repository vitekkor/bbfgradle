// Original bug: KT-39757

fun main() {
    sequenceOf("Foo", "Bar")
        .filter {
            it.startsWith("F") // break-point (1) here
        }
        .forEach {
            println(it) // break-point (2) here
        }
}
