// Original bug: KT-8246

fun foo(): String {
    OUTER@while (true) {
        try {
            while (true) {
                continue@OUTER // Warning:(5, 17) Kotlin: Unreachable code
            }
        } finally {
            return "OK"
        }
    }
}

fun main(args: Array<String>) {
    println(foo())
}
