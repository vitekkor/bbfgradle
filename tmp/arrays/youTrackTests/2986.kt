// Original bug: KT-31923

fun main() {
    for (i in 1..10) {
        try {
            try {
                break // return or continue
            } catch (fail: Exception) {
                println("catch")
            }
        } finally {
            println("finally")
            throw RuntimeException()
        }
    }
}
