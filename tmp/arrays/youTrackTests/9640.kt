// Original bug: KT-8246

fun main(args: Array<String>) {
    OUTER@while (true) {
        try {
            while (true) {
                continue@OUTER
            }
        } finally {
            break // Error:(8, 13) Kotlin: 'break' and 'continue' are only allowed inside a loop
        }
    }
    println("OK") // Yellow: Unreachable code
}
