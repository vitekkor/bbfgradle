// Original bug: KT-43421

fun main() {
    do {
        try {
            println("Good")
            return
        } finally {
            break
        }
    } while (false)
    println("Bad")
}
