// Original bug: KT-43421

fun main() {
    try {
        try {
            println("Good")
            return
        } finally {
            throw Error()
        }
    } catch (e: Error) {
    }
    println("Bad")
}
