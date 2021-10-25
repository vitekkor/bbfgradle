// Original bug: KT-44745

val strings = arrayOf("1a", "1b", "2", "3")

fun main() {
    for (s in strings) {
        if (s == "1a" || s == "1b") {
            continue
        }
        if (s == "2") {
            continue
        }
        println(s)
    }
}
